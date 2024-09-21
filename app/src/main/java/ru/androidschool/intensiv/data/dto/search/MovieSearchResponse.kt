package ru.androidschool.intensiv.data.dto.search

import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResultItem

@Serializable
data class MovieSearchResponse(
    val page: Int,
    val results: List<MovieSearchResultItem>,
    val total_pages: Int,
    val total_results: Int
)