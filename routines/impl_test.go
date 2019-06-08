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

package routines_test

import (
	"fmt"
	"log"
	"org.orange/routines"
	"testing"
	"time"
)

type testLogger struct {

}

func (t *testLogger) Debugf(template string, args ...interface{}) {
	fmt.Println(fmt.Sprintf(template, args...))
}

func (t *testLogger) Infof(template string, args ...interface{}) {
	fmt.Println(fmt.Sprintf(template, args...))
}

func (t *testLogger) Errorf(template string, args ...interface{}) {
	fmt.Println(fmt.Sprintf(template, args...))
}

type workResult struct {
	key string
	value interface{}
	err error
}


func (w *workResult) GetKey() string {
	return w.key
}

func (w *workResult) GetValue() interface{} {
	return w.value
}

func (w *workResult) GetErr() error {
	return w.err
}

func TestFactory_OnJobsDone(t *testing.T) {
	duration := 2 * time.Second
	rMap := make(map[string]int, 0)
	option := &routines.Option{
		FinalType: routines.FinalizeOnJobsDone,
		Logger: &testLogger{},
		TickerDuration: &duration,
		WorkProcessor: func(job *routines.Job, resultChan chan routines.WorkResult, stopChan <-chan struct{}) error {
			for k := range job.WorkPackages {
				<- time.After(100 * time.Millisecond)
				resultChan <- &workResult{
					key: k,
					value: 200,
				}
			}
			return nil
		},
		Interceptor: routines.EventInterceptor{
			OnResultReceived: func(wr routines.WorkResult) error {
				rMap[wr.GetKey()] = wr.GetValue().(int)
				return nil
			},
		},
	}
	factory, err := routines.NewFactory(option)
	if nil != err {
		log.Fatal(err)
	}
	inJob := &routines.Job{
		Name: "counter",
		WorkPackages: make(map[string]interface{}, 1000),
	}
	for idx:= 0; idx < 1000; idx++ {
		inJob.WorkPackages[fmt.Sprintf("job-%d", idx)] = true
	}
	err = factory.Start(inJob)
	if nil != err {
		log.Fatal(err)
	}
	err = factory.Wait()
	if nil != err {
		log.Fatal(err)
	}
	if len(rMap) != 1000 {
		log.Fatalf("size must be 1000")
	}
	for k, v := range rMap {
		if v != 200 {
			log.Fatalf("value is not 200 for key %s", k)
		}
	}

}