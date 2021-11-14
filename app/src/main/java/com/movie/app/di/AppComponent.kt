package com.movie.app.di

import com.movie.app.basekit.ImageDownloader
import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.basekitimpl.di.BaseKitModule
import com.movie.app.remote.di.RemoteModule
import com.movie.app.repositorykit.MovieRepository
import com.movie.app.repositorykitimpl.repository.di.MovieListRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseKitModule::class, MovieListRepositoryModule::class, RemoteModule::class])
interface AppComponent {
    fun toastMaker(): ToastMaker
    fun schedulerProvider(): SchedulerProvider
    fun movieRepository(): MovieRepository
    fun imageDownloader(): ImageDownloader
}