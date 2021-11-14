package com.movie.app.landingscreen.di.module

import androidx.lifecycle.ViewModel
import com.movie.app.landingscreen.viewmodel.MovieDetailsViewModel
import com.movie.app.landingscreen.viewmodel.MovieListViewModel
import com.movie.app.landingscreen.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMainViewModel(myViewModel: MovieListViewModel): ViewModel
}