package com.movie.app.landingscreen.viewmodel

import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapter
import com.movie.app.landingscreen.utils.*
import com.movie.app.repositorykit.MovieRepository
import com.movie.models.Movie
import com.movie.models.MovieAPIResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieListViewModelTest {
    private lateinit var viewModel: MovieListViewModel
    private val movieRepository: MovieRepository = mock()
    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()
    private val recyclerViewAdapter: MovieAppRecyclerViewAdapter = mock()
    private val toastMaker: ToastMaker = mock()
    private val page = 1

    @Before
    fun setUp() {
        viewModel =
            MovieListViewModel(movieRepository, schedulerProvider, recyclerViewAdapter, toastMaker)
    }

    @Test
    fun `verify loadDataInit when apiResult is empty`() {
        whenever(
            movieRepository.searchMovies(
                DEFAULT_SEARCH_TEXT,
                page
            )
        ).thenReturn(Single.just(mutableListOf()))
        viewModel.loadDataInit()
        Assert.assertEquals(0, viewModel.apiResult.size)
    }

    @Test
    fun `verify loadDataInit when apiResult not empty`() {
        val apiResponse = createMovieAPIResponse()

        whenever(
            movieRepository.searchMovies(
                DEFAULT_SEARCH_TEXT,
                page
            )
        ).thenReturn(Single.just(apiResponse.search))
        viewModel.loadDataInit()
        viewModel.loadDataInit()
        Assert.assertEquals(apiResponse.search, viewModel.apiResult)
        Assert.assertEquals(2, viewModel.apiResult.size)
    }

    @Test
    fun loadMoreData() {
        val apiResponse = createMovieAPIResponse()

        whenever(
            movieRepository.searchMovies(
                DEFAULT_SEARCH_TEXT,
                2
            )
        ).thenReturn(Single.just(apiResponse.search))
        viewModel.loadMoreData()

        Assert.assertEquals(apiResponse.search, viewModel.apiResult)
    }

    private fun createMovieAPIResponse(): MovieAPIResponse {
        val movies: MutableList<Movie> = mutableListOf()
        val movie1: Movie = mock()
        val movie2: Movie = mock()
        movies.add(movie1)
        movies.add(movie2)

        return MovieAPIResponse(
            search = movies,
            response = "true",
            totalResults = "2"
        )
    }
}