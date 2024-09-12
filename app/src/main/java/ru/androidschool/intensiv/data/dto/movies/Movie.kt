package ru.androidschool.intensiv.data.dto.movies

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.BuildConfig

@Serializable
data class Movie(
    val adult: Boolean,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val vote_count: Int
){
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
        get() = "${BuildConfig.BASE_IMAGE_URL}${field}"

    @SerializedName("poster_path")
    val posterPath: String? = null
        get() = "${BuildConfig.BASE_IMAGE_URL}${field}"
}