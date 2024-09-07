package ru.androidschool.intensiv.data.dto.search

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    val page: Int,
    val results: List<MovieSearchResultItem>,
    val total_pages: Int,
    val total_results: Int
)