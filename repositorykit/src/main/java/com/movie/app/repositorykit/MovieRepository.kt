package com.movie.app.repositorykit

import com.movie.models.Movie
import io.reactivex.Single

/**
 * repository to be used by the entire app, implementation in repositorykitimpl module
 */
interface MovieRepository {
    fun searchMovies(
        searchTerm: String,
        page: Int
    ): Single<List<Movie>>
}