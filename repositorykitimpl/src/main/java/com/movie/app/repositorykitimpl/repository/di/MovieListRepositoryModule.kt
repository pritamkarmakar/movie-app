package com.movie.app.repositorykitimpl.repository.di

import com.movie.app.basekit.SchedulerProvider
import com.movie.app.remote.api.MovieApi
import com.movie.app.repositorykit.MovieRepository
import com.movie.app.repositorykitimpl.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieListRepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryKit(
        movieApi: MovieApi,
        schedulerProvider: SchedulerProvider
    ): MovieRepository = MovieRepositoryImpl(movieApi, schedulerProvider)
}