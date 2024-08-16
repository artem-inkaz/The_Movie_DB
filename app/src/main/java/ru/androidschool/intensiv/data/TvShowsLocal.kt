package ru.androidschool.intensiv.data

data class TvShowsLocal(
    val name: String? = "",
    val voteAverage: Double = 0.0,
    val backdropPath: String?,
    val posterPath: String?
)