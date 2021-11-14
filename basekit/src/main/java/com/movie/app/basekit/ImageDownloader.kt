package com.movie.app.basekit

import android.widget.ImageView

interface ImageDownloader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}