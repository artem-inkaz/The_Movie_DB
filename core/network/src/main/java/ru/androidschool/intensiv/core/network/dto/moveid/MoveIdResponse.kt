package ru.androidschool.intensiv.core.network.dto.moveid

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.core.network.utils.movieParams

@Serializable
data class MoveIdResponse(
    val adult: Boolean,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val vote_count: Int
) {
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
        get() = "${movieParams.baseImageUrl}${field}"

    @SerializedName("poster_path")
    val posterPath: String? = null
        get() = "${movieParams.baseImageUrl}${field}"
}