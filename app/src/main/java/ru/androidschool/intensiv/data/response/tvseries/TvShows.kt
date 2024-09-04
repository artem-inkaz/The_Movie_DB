package ru.androidschool.intensiv.data.response.tvseries

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.BuildConfig

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
        get() = "${BuildConfig.BASE_IMAGE_URL}${field}"

    @SerializedName("poster_path")
    val posterPath: String? = null
        get() = "${BuildConfig.BASE_IMAGE_URL}${field}"
}