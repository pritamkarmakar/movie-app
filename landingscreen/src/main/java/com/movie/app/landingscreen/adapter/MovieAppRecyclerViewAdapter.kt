package com.movie.app.landingscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movie.app.landingscreen.navigator.Navigator
import com.movie.app.basekit.ImageDownloader
import com.movie.app.landingscreen.R
import com.movie.app.landingscreen.databinding.IndividualMovieCardBinding
import com.movie.models.Movie

/**
 * Recycler view adapater contract
 */
abstract class MovieAppRecyclerViewAdapter :
    RecyclerView.Adapter<MovieAppRecyclerViewAdapterImpl.MovieViewHolder>() {
    abstract fun setData(article: List<Movie>)
    abstract fun clearData()
}

/**
 * Recycler view adapater contract implementation
 */
class MovieAppRecyclerViewAdapterImpl(
    private val navigator: Navigator,
    private val imageDownloader: ImageDownloader
) : MovieAppRecyclerViewAdapter() {
    private val result: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.individual_movie_card, parent, false)
        val binding = DataBindingUtil.bind<IndividualMovieCardBinding>(view)
        val viewHolder = MovieViewHolder(view)
        binding?.holder = viewHolder
        return viewHolder
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieData = result[position]
        holder.run {
            title.text = movieData.title
            director.text = movieData.director
            year.text = movieData.year
            plot.text = movieData.plot
            movieData.poster?.let { imageDownloader.loadImage(it, holder.image) }
        }

        holder.itemView.setOnClickListener {
            navigator.launchMovieDetails(movieData)
        }
    }

    override fun setData(data: List<Movie>) {
        val lastPos = result.size
        this.result.addAll(data)
        notifyItemRangeInserted(lastPos, data.size + 1)
    }

    override fun clearData() {
        val previousContentSize = result.size
        this.result.clear()
        notifyItemRangeRemoved(0, previousContentSize);
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.movie_poster)
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val director: TextView = itemView.findViewById(R.id.movie_director)
        val year: TextView = itemView.findViewById(R.id.movie_year)
        val plot: TextView = itemView.findViewById(R.id.movie_plot)
    }
}