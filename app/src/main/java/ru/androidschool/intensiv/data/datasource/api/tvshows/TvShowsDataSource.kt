package ru.androidschool.intensiv.data.datasource.api.tvshows

import ru.androidschool.intensiv.core.network.dto.tvseries.TvShowsResponse

interface TvShowsDataSource {
    suspend fun getPopularTvShows(): TvShowsResponse
}