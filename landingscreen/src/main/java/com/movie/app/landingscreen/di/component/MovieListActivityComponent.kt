package com.movie.app.landingscreen.di.component

import com.movie.app.basekit.ImageDownloader
import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.landingscreen.di.module.MovieListModule
import com.movie.app.landingscreen.di.module.ViewModelFactoryModule
import com.movie.app.landingscreen.di.module.ViewModelsModule
import com.movie.app.landingscreen.view.MovieDetailsActivity
import com.movie.app.landingscreen.view.MovieListActivity
import com.movie.app.repositorykit.MovieRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [MovieListDependencies::class], modules = [ViewModelFactoryModule::class, ViewModelsModule::class, MovieListModule::class])
interface MovieListActivityComponent {
    fun inject(activity: MovieListActivity)
    fun inject(activity: MovieDetailsActivity)
}

interface MovieListDependenciesProvider{
    fun dependencyProvider(): MovieListDependencies
}

interface MovieListDependencies{
    fun toastMaker(): ToastMaker
    fun schedulerProvider(): SchedulerProvider
    fun movieRepository(): MovieRepository
    fun imageDownloader(): ImageDownloader
}