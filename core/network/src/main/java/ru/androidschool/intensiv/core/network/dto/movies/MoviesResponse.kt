package ru.androidschool.intensiv.core.network.dto.movies

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)