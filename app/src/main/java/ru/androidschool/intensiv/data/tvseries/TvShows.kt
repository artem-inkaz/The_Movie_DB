package ru.androidschool.intensiv.data.tvseries

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TvShows(
    val adult: Boolean,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val vote_count: Int
){
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
        get() = "https://image.tmdb.org/t/p/w500$field"

    @SerializedName("poster_path")
    val posterPath: String? = null
        get() = "https://image.tmdb.org/t/p/w300$field"
}