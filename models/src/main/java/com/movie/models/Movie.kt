package com.movie.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieAPIResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Movie>?,
    @SerializedName("totalResults")
    val totalResults: String
)

@Parcelize
data class Movie(
    @SerializedName("Actors")
    val actors: String? = null,
    @SerializedName("Awards")
    val awards: String? = null,
    @SerializedName("Country")
    val country: String? = null,
    @SerializedName("Director")
    val director: String? = null,
    @SerializedName("Genre")
    val genre: String? = null,
    @SerializedName("imdbID")
    val imdbID: String? = null,
    @SerializedName("imdbRating")
    val imdbRating: String? = null,
    @SerializedName("imdbVotes")
    val imdbVotes: String? = null,
    @SerializedName("Language")
    val language: String? = null,
    @SerializedName("Metascore")
    val metascore: String? = null,
    @SerializedName("Plot")
    val plot: String? = null,
    @SerializedName("Poster")
    val poster: String? = null,
    @SerializedName("Rated")
    val rated: String? = null,
    @SerializedName("Ratings")
    val ratings: List<Rating>? = null,
    @SerializedName("Released")
    val released: String? = null,
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Runtime")
    val runtime: String? = null,
    @SerializedName("Title")
    val title: String? = null,
    @SerializedName("totalSeasons")
    val totalSeasons: String? = null,
    @SerializedName("Type")
    val type: String? = null,
    @SerializedName("Writer")
    val writer: String? = null,
    @SerializedName("Year")
    val year: String? = null
) : Parcelable

@Parcelize
data class Rating(
    @SerializedName("Source")
    val source: String? = null,
    @SerializedName("Value")
    val value: String? = null
) : Parcelable