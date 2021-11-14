package com.movie.app.repositorykitimpl.repository

import com.movie.app.basekit.SchedulerProvider
import com.movie.app.remote.api.MovieApi
import com.movie.app.repositorykit.MovieRepository
import com.movie.models.Movie
import io.reactivex.Single

/**
 * repository for the app, instance will be available through AppComponent
 */
class MovieRepositoryImpl(
    private val moviesApi: MovieApi,
    private val schedulerProvider: SchedulerProvider
) :
    MovieRepository {
    override fun searchMovies(
        searchTerm: String,
        page: Int
    ): Single<List<Movie>> {

        return moviesApi.fetchMovies(searchTerm, page)
            .subscribeOn(schedulerProvider.ioSchedulerProvider())
            .toObservable()
            .filter { it.search != null }
            .flatMapIterable {
                it.search
            }
            .flatMap { movie ->
                movie.imdbID?.let { imdbID ->
                    moviesApi.fetchMovieDetails(imdbID)
                        .subscribeOn(schedulerProvider.ioSchedulerProvider())
                        .toObservable()
                }
            }
            .toList()
    }
}