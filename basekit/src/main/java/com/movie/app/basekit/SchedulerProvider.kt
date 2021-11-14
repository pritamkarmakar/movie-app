package com.movie.app.basekit

import io.reactivex.Scheduler

/**
 * SchedulerProvider contract, implementation is in basekitimpl module
 */
interface SchedulerProvider {
    fun ioSchedulerProvider(): Scheduler
    fun uiSchedulerProvider(): Scheduler
}