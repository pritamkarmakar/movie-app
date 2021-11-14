package com.movie.app.basekitimpl

import com.movie.app.basekit.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * IO and UI scheduler provider, instance will be available through appcomponent
 */
open class SchedulerProviderImpl : SchedulerProvider {
    override fun ioSchedulerProvider(): Scheduler = Schedulers.io()
    override fun uiSchedulerProvider(): Scheduler = AndroidSchedulers.mainThread()
}