package ru.androidschool.intensiv.data.response.movies

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)