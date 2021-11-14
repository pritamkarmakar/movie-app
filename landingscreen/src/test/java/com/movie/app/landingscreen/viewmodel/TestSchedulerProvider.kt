package com.movie.app.landingscreen.viewmodel

import com.movie.app.basekit.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {
    override fun ioSchedulerProvider(): Scheduler = Schedulers.trampoline()
    override fun uiSchedulerProvider(): Scheduler = Schedulers.trampoline()
}