package com.movie.app

import android.app.Application
import com.movie.app.basekit.ImageDownloader
import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.basekitimpl.di.BaseKitModule
import com.movie.app.di.AppComponent
import com.movie.app.di.DaggerAppComponent
import com.movie.app.landingscreen.di.component.MovieListDependencies
import com.movie.app.landingscreen.di.component.MovieListDependenciesProvider
import com.movie.app.repositorykit.MovieRepository

class MainApp : Application(), MovieListDependenciesProvider {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        setupDI()
        super.onCreate()
    }

    private fun setupDI() {
        appComponent = DaggerAppComponent
            .builder()
            .baseKitModule(BaseKitModule(this))
            .build()
    }

    override fun dependencyProvider(): MovieListDependencies {
        return object : MovieListDependencies {
            override fun toastMaker(): ToastMaker {
                return appComponent.toastMaker()
            }

            override fun schedulerProvider(): SchedulerProvider {
                return appComponent.schedulerProvider()
            }

            override fun movieRepository(): MovieRepository {
                return appComponent.movieRepository()
            }

            override fun imageDownloader(): ImageDownloader {
                return appComponent.imageDownloader()
            }

        }
    }
}