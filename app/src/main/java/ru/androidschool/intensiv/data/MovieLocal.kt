package ru.androidschool.intensiv.data

data class MovieLocal(
    val id: Int,
    val title: String? = "",
    val overview: String,
    val voteAverage: Double = 0.0,
    val backdropPath: String?,
    val posterPath: String?
)