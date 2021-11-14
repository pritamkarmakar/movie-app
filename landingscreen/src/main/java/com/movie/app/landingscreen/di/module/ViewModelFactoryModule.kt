package com.movie.app.landingscreen.di.module

import androidx.lifecycle.ViewModelProvider
import com.movie.app.landingscreen.viewmodel.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}