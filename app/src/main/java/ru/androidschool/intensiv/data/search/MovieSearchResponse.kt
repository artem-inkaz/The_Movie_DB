package ru.androidschool.intensiv.data.search

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    val page: Int,
    val results: List<MovieSearch>,
    val total_pages: Int,
    val total_results: Int
)