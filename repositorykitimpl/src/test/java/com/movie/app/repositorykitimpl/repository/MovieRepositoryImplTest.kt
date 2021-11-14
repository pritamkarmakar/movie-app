package com.movie.app.repositorykitimpl.repository

import com.movie.app.basekit.SchedulerProvider
import com.movie.app.remote.api.MovieApi
import com.movie.app.repositorykit.MovieRepository
import com.movie.models.Movie
import com.movie.models.MovieAPIResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {
    private lateinit var repository: MovieRepository
    private val movieAPI: MovieApi = mock()
    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()
    private val movieAPIResponse: MovieAPIResponse = createMovieAPIResponse()
    private val query = "query"
    private val page = 0;

    @Before
    fun setup() {
        repository = MovieRepositoryImpl(movieAPI, schedulerProvider)
    }

    @Test
    fun `verify searchMovies`() {
        val apiResult = Single.just(movieAPIResponse)
        whenever(
            movieAPI.fetchMovies(
                query,
                page
            )
        ).thenReturn(apiResult)

        whenever(movieAPI.fetchMovieDetails(any())).thenReturn(Single.just(Movie(imdbID = "123")))

        repository.searchMovies(query, page).test()

        verify(movieAPI).fetchMovieDetails("123")
        verify(movieAPI).fetchMovieDetails("456")
    }

    private fun createMovieAPIResponse(): MovieAPIResponse {
        val movies: MutableList<Movie> = mutableListOf()
        val movie1 = Movie(imdbID = "123")
        val movie2 = Movie(imdbID = "456")
        movies.add(movie1)
        movies.add(movie2)

        return MovieAPIResponse(
            search = movies,
            response = "true",
            totalResults = "2"
        )
    }
}

class TestSchedulerProvider : SchedulerProvider {
    override fun ioSchedulerProvider(): Scheduler = Schedulers.trampoline()
    override fun uiSchedulerProvider(): Scheduler = Schedulers.trampoline()
}