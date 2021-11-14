package com.movie.app.landingscreen.bindingadapter

import android.widget.AbsListView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.basekit.ImageDownloader
import com.movie.app.landingscreen.adapter.MovieAppRecyclerViewAdapter
import com.movie.app.landingscreen.viewmodel.MovieListViewModel
import com.movie.models.Movie

@BindingAdapter("data", "searchTermChanged")
fun updateRecyclerView(recyclerView: RecyclerView, data: List<Movie>?, searchTermChanged: Boolean) {
    val adapter = recyclerView.adapter as? MovieAppRecyclerViewAdapter
    if (searchTermChanged) {
        adapter?.clearData()
    }
    data?.let { adapter?.setData(it) }
}

@BindingAdapter("app:addOnScrollListener")
fun addOnScrollListener(
    recyclerView: RecyclerView,
    viewModel: MovieListViewModel
) {
    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisible = layoutManager.findFirstVisibleItemPosition()
            viewModel.run {
                if (isScrolling.get() && (visibleItemCount + firstVisible) >= totalItemCount) {
                    isScrolling.set(false)
                    loadMoreData()
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                viewModel.isScrolling.set(true)
            }
        }
    })
}

@BindingAdapter("imageUrl", "imageDownloader")
fun addImage(imageView: AppCompatImageView, imageUrl: String, imageDownloader: ImageDownloader) {
    imageDownloader.loadImage(imageUrl, imageView)
}