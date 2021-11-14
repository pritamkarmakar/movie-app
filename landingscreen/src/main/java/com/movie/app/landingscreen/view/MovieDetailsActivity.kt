package com.movie.app.landingscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.movie.app.basekit.ImageDownloader
import com.movie.app.landingscreen.R
import com.movie.app.landingscreen.databinding.ActivityMovieDetailsBinding
import com.movie.app.landingscreen.di.component.DaggerMovieListActivityComponent
import com.movie.app.landingscreen.di.component.MovieListDependencies
import com.movie.app.landingscreen.di.component.MovieListDependenciesProvider
import com.movie.app.landingscreen.di.module.MovieListModule
import com.movie.app.landingscreen.viewmodel.MovieDetailsViewModel
import com.movie.app.landingscreen.viewmodel.MovieListViewModel
import com.movie.models.Movie
import javax.inject.Inject

internal const val MOVIE_DETAILS = "MOVIE_DETAILS"

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieDetailsViewModel
    @Inject
    lateinit var imageDownloader: ImageDownloader
    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        viewModel = MovieDetailsViewModel()
        binding.viewModel = viewModel
        val data = intent.getParcelableExtra<Movie>(MOVIE_DETAILS)
        viewModel.initData(data)
        binding.imageDownloader = imageDownloader
    }

    /**
     * Build Dagger dependency graph
     */
    private fun setupDI() {
        val dependencies: MovieListDependencies =
            (applicationContext as MovieListDependenciesProvider).dependencyProvider()

        val component = DaggerMovieListActivityComponent
            .builder()
            .movieListDependencies(dependencies)
            .movieListModule(MovieListModule(this))
            .build()
        component.inject(this)
    }
}