package ru.androidschool.intensiv.data.dto.movies

import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.core.network.dto.movies.Dates
import ru.androidschool.intensiv.core.network.dto.movies.Movie

@Serializable
data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)