package com.movie.app.basekitimpl

import android.widget.ImageView
import com.movie.app.basekit.ImageDownloader
import com.squareup.picasso.Picasso

class ImageDownloaderImpl : ImageDownloader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.movie_splash)
            .into(imageView);
    }
}