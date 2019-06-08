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

import "time"

type Factory interface {

	// start the factory workers to do the job
	Start(job *Job) error

	// wait the job to be done
	Wait() error

	// interrupt and terminate the jobs
	Stop()
}

type Job struct {

	Name string

	// return the work packages for the job
	WorkPackages map[string]interface{}
}

type WorkResult interface {
	GetKey() string

	GetValue() interface{}

	GetErr() error
}

type FinalizeType int

const (
	FinalizeOnWorkersExit = 0x11
	FinalizeOnJobsDone    = 0x12
)

type EventInterceptor struct {
	BeforeWorkerStarted func() error

	OnResultReceived func(WorkResult) error

	AfterWorkerFinalized func() error
}

type Option struct {
	WorkerCount int

	FinalType FinalizeType

	WorkProcessor func(job *Job, resultChan chan WorkResult, stopChan <-chan struct{}) error

	Interceptor EventInterceptor

	Logger Logger

	TickerDuration *time.Duration
}
