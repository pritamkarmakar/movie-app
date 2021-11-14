package com.movie.app.landingscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.landingscreen.R
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapter
import com.movie.app.landingscreen.databinding.ActivityMovieListBinding
import com.movie.app.landingscreen.di.component.DaggerMovieListActivityComponent
import com.movie.app.landingscreen.di.component.MovieListDependencies
import com.movie.app.landingscreen.di.component.MovieListDependenciesProvider
import com.movie.app.landingscreen.di.module.MovieListModule
import com.movie.app.landingscreen.viewmodel.MovieListViewModel
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    @Inject
    lateinit var recyclerViewAdapter: MovieAppRecyclerViewAdapter
    private lateinit var databinding: ActivityMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModeFactory).get(MovieListViewModel::class.java)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        databinding.viewmodel = viewModel
        this.viewModel.loadDataInit()
        // setting up recycler view
        recyclerView = databinding.articleListRecyclerView
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
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
