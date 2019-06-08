// Copyright 2019 Orange Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package routines

import (
	"context"
	"fmt"
	"github.com/joomcode/errorx"
	"go.uber.org/multierr"
	"runtime"
	"sync"
	"time"
)

func validateOption(option *Option) error {
	var errs error
	if nil == option.Logger {
		errs = multierr.Append(errs, fmt.Errorf("logger must be set"))
	}
	if nil == option.WorkProcessor {
		errs = multierr.Append(errs, fmt.Errorf("worker processor must be set"))
	}
	if option.FinalType != FinalizeOnJobsDone && option.FinalType != FinalizeOnWorkersExit {
		errs = multierr.Append(errs, fmt.Errorf("final type must be set"))
	}
	if nil == option.TickerDuration {
		errs = multierr.Append(errs, fmt.Errorf("ticker time must be set"))
	}
	return errs
}

type factory struct {
	option *Option

	workers []chan *Job

	workerCount int

	ctx context.Context

	cancel context.CancelFunc

	waitGroup *sync.WaitGroup

	valueSet map[string]bool

	totalWorkCount uint32

	jobName string

	resultChan chan WorkResult
}

func NewFactory(option *Option) (Factory, error) {
	var err error
	if err = validateOption(option); nil != err {
		return nil, errorx.Decorate(err, "fail to validate option")
	}
	factory := &factory{
		option:         option,
		waitGroup:      &sync.WaitGroup{},
		totalWorkCount: 0,
		resultChan:     make(chan WorkResult, 0),
	}
	if option.WorkerCount <= 0 {
		factory.workerCount = runtime.NumCPU() * 2
	} else {
		factory.workerCount = option.WorkerCount
	}
	option.Logger.Debugf("factory using workers count %d", factory.workerCount)
	factory.workers = make([]chan *Job, 0, factory.workerCount)
	for idx := 0; idx < factory.workerCount; idx++ {
		factory.workers = append(factory.workers, make(chan *Job, 1))
	}
	factory.ctx, factory.cancel = context.WithCancel(context.Background())
	return factory, nil
}

// start the factory workers to do the job
func (f *factory) Start(job *Job) error {
	var err error
	if nil == job || len(job.WorkPackages) == 0 {
		return errorx.IllegalArgument.New("empty job received")
	}
	if len(job.Name) == 0 {
		return errorx.IllegalArgument.New("job name can't be empty")
	}
	f.jobName = job.Name
	beforeWorkerStarted := f.option.Interceptor.BeforeWorkerStarted
	if nil != beforeWorkerStarted {
		if err = beforeWorkerStarted(); nil != err {
			return errorx.Decorate(err, "fail to run interceptor <BeforeWorkerStarted>")
		}
	}
	f.totalWorkCount = uint32(len(job.WorkPackages))
	jobMap := make(map[int]*Job, f.workerCount)
	f.valueSet = make(map[string]bool, len(job.WorkPackages))
	index := 0
	for k, v := range job.WorkPackages {
		workerIndex := index % f.workerCount
		index++
		f.valueSet[k] = true
		interJob, exists := jobMap[workerIndex]
		if exists {
			interJob.WorkPackages[k] = v
		} else {
			interJob = &Job{
				WorkPackages: make(map[string]interface{}, 0),
			}
			interJob.WorkPackages[k] = v
			jobMap[workerIndex] = interJob
		}
	}
	for idx := 0; idx < f.workerCount; idx++ {
		_, exists := jobMap[idx]
		if !exists {
			jobMap[idx] = &Job{
				WorkPackages: make(map[string]interface{}, 0),
			}
		}
	}
	// start worker goroutine
	f.waitGroup.Add(f.workerCount)
	for idx, workChan := range f.workers {
		workChan <- jobMap[idx]
		go f.processWorkPackage(idx, workChan)
	}
	f.option.Logger.Infof("factory start successfully, worker count %d", f.workerCount)
	return nil
}

func (f *factory) processWorkPackage(idx int, workChan chan *Job) {
	log := f.option.Logger
	log.Debugf("worker %d started", idx)
	defer func() {
		log.Debugf("worker %d stopped", idx)
		f.waitGroup.Done()
	}()
	select {
	case job := <-workChan:
		err := f.option.WorkProcessor(job, f.resultChan, f.ctx.Done())
		if nil != err {
			log.Errorf("fail to process work in worker %d, error %+v", idx, err)
			return
		}
	case <-f.ctx.Done():
		f.option.Logger.Errorf("worker %d stopped unexpectly", idx)
		return
	}
}

// wait the job to be done
func (f *factory) Wait() error {
	if len(f.jobName) == 0 {
		return errorx.IllegalState.New("job has not be inserted")
	}
	log := f.option.Logger
	allDoneChan := make(chan struct{})
	go func() {
		f.waitGroup.Wait()
		close(allDoneChan)
	}()
	var allWorkerDone = false
	var processed uint32 = 0
	var err error
	ticker := time.NewTicker(*f.option.TickerDuration)
	defer func() {
		ticker.Stop()
		f.cancel()
	}()
	for {
		select {
		case <-allDoneChan:
			allWorkerDone = true
			afterWorkerFinalized := f.option.Interceptor.AfterWorkerFinalized
			if nil != afterWorkerFinalized {
				err = afterWorkerFinalized()
				if nil != err {
					return err
				}
			}
			if f.option.FinalType == FinalizeOnWorkersExit {
				return nil
			}
			if allWorkerDone && len(f.valueSet) == 0 {
				return nil
			}
		case result := <-f.resultChan:
			if nil != result.GetErr() {
				log.Errorf("error received on result %s, error is %+v", result.GetKey(), result.GetErr())
				return result.GetErr()
			}
			processed++
			onResultReceived := f.option.Interceptor.OnResultReceived
			if nil != onResultReceived {
				err = onResultReceived(result)
				if nil != err {
					return err
				}
			}
			delete(f.valueSet, result.GetKey())
			if allWorkerDone && len(f.valueSet) == 0 {
				return nil
			}
		case <-ticker.C:
			log.Infof("Job %s processing progress: %d/%d", f.jobName, processed, f.totalWorkCount)
		}
	}
	return nil
}

// interrupt and terminate the jobs
func (f *factory) Stop() {
	f.cancel()
}
