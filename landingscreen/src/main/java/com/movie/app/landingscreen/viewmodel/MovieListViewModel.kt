package com.movie.app.landingscreen.viewmodel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.movie.app.basekit.SchedulerProvider
import com.movie.app.basekit.ToastMaker
import com.movie.app.landingscreen.R
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapter
import com.movie.app.landingscreen.utils.*
import com.movie.app.repositorykit.MovieRepository
import com.movie.models.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulerProvider: SchedulerProvider,
    private val recyclerViewAdapter: MovieAppRecyclerViewAdapter,
    private val toastMaker: ToastMaker
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    // API result
    val apiResult = mutableListOf<Movie>()
    // observable to listen to user search text change
    private val searchTerm = PublishSubject.create<String>()
    var progressbarVisibility: ObservableInt = ObservableInt(View.INVISIBLE)
    var isScrolling: ObservableBoolean = ObservableBoolean(false)
    // keep track of last used search text, this is useful when we will load more data for scrolling
    private var lastSearchTerm = DEFAULT_SEARCH_TEXT
    // start page number for API pagination
    var page = 1

    fun loadDataInit() {
        if (apiResult.isEmpty()) {
            // for new view model init use the default search term
            makeApiCall(DEFAULT_SEARCH_TEXT)
        } else {
            // view model is being reused by the activity so use existing fetched data, no new API call will be invoked
            recyclerViewAdapter.setData(apiResult)
        }
        // invoke API call for search term change
        searchTerm
            .map { text -> text.toLowerCase().trim() }
            .debounce(350, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotBlank() }
            .subscribe { text ->
                lastSearchTerm = text
                page = 1
                makeApiCall(text, isNewSearchTerm = true)
            }

    }

    fun onSearchTextChanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {
        // if user clears all the search text then use default search text
        if (s.toString().isNullOrEmpty()) searchTerm.onNext(DEFAULT_SEARCH_TEXT)
        else searchTerm.onNext(s.toString())
    }

    /**
     * to load more data once user starts scrolling
     */
    fun loadMoreData() {
        page += 1
        makeApiCall(lastSearchTerm)
    }

    private fun makeApiCall(searchText: String, isNewSearchTerm: Boolean = false) {
        compositeDisposable.add(
            movieRepository.searchMovies(
                searchText,
                page
            )
                .subscribeOn(schedulerProvider.ioSchedulerProvider())
                .doOnSubscribe { progressbarVisibility.set(View.VISIBLE) }
                .observeOn(schedulerProvider.uiSchedulerProvider())
                .doFinally { progressbarVisibility.set(View.INVISIBLE) }
                .subscribeBy(
                    onSuccess = {
                        if (it.isEmpty()) {
                            toastMaker.showToast(R.string.api_response_empty)
                            return@subscribeBy
                        }
                        // when user change the search term we want to clear all the previous data
                        if (isNewSearchTerm) {
                            recyclerViewAdapter.clearData()
                        }
                        recyclerViewAdapter.setData(it)
                        apiResult.addAll(it)
                    },
                    onError = {
                        toastMaker.showToast(R.string.server_error_message)
                    }
                )
        )
    }

    override fun onCleared() {
        // dispose all disposable
        super.onCleared()
        compositeDisposable.clear()
    }
}