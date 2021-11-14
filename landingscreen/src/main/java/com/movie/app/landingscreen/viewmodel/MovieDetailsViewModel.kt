package com.movie.app.landingscreen.viewmodel

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.ObservableField
import com.movie.app.basekit.ImageDownloader
import com.movie.models.Movie

class MovieDetailsViewModel {
    val title = ObservableField<String>()
    val poster = ObservableField<String>()
    val director = ObservableField<String>()
    val year = ObservableField<String>()
    val plot = ObservableField<String>()

    fun initData(movieData: Movie) {
        title.set(movieData.title)
        poster.set(movieData.poster)
        director.set(movieData.director)
        year.set(movieData.year)
        plot.set(movieData.plot)
    }

    fun goPrevious(view: View) {
        (view.context as Activity).finish()
    }
}