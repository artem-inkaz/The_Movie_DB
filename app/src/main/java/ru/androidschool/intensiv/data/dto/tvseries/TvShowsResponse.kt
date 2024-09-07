package ru.androidschool.intensiv.data.dto.tvseries

import kotlinx.serialization.Serializable

@Serializable
data class TvShowsResponse(
    val page: Int,
    val results: List<TvShows>,
    val total_pages: Int,
    val total_results: Int
)