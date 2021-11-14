package com.movie.app.remote.api

import com.movie.models.Movie
import com.movie.models.MovieAPIResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(".")
    fun fetchMovies(
        @Query("s") searchTerm: String,
        @Query("page") page: Int
    ): Single<MovieAPIResponse>

    @GET(".")
    fun fetchMovieDetails(
        @Query("i") imdbID: String
    ): Single<Movie>
}