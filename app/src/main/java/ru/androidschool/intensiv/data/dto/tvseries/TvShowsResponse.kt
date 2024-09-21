package ru.androidschool.intensiv.data.dto.tvseries

import kotlinx.serialization.Serializable
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShows

@Serializable
data class TvShowsResponse(
    val page: Int,
    val results: List<TvShows>,
    val total_pages: Int,
    val total_results: Int
)