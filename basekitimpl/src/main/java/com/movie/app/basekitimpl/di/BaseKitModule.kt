package com.movie.app.basekitimpl.di

import android.content.Context
import com.movie.app.basekit.ImageDownloader
import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.basekitimpl.ImageDownloaderImpl
import com.movie.app.basekitimpl.SchedulerProviderImpl
import com.movie.app.basekitimpl.ToastMakerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseKitModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Singleton
    @Provides
    fun provideToastMaker(): ToastMaker = ToastMakerImpl(context)

    @Provides
    @Singleton
    fun provideImageDownloader(): ImageDownloader = ImageDownloaderImpl()
}