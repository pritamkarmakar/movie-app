package com.movie.app.landingscreen.di.module

import androidx.appcompat.app.AppCompatActivity
import com.movie.app.basekit.ImageDownloader
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapter
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapterImpl
import com.movie.app.landingscreen.navigator.Navigator
import com.movie.app.landingscreen.navigator.NavigatorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieListModule(private val activity: AppCompatActivity) {
    @Provides
    @Singleton
    fun provideMovieListAdapter(
        imageDownloader: ImageDownloader,
        navigator: Navigator
    ): MovieAppRecyclerViewAdapter =
        MovieAppRecyclerViewAdapterImpl(navigator, imageDownloader)

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl(activity)
}