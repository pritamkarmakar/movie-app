package com.movie.app.landingscreen.navigator

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.movie.app.landingscreen.view.MovieDetailsActivity
import com.movie.app.landingscreen.view.MOVIE_DETAILS
import com.movie.models.Movie
import com.movie.models.Rating
import kotlinx.android.parcel.Parcelize

interface Navigator {
    fun launchMovieDetails(movie: Movie)
}

class NavigatorImpl(private val context: Context) : Navigator {
    override fun launchMovieDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_DETAILS, movie)
        context.startActivity(intent)
    }
}