package ru.androidschool.intensiv.data.datasource.api.tvshows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShowsResponse
import javax.inject.Inject

class TvShowsNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    TvShowsDataSource {
    override suspend fun getPopularTvShows(): TvShowsResponse {
        return withContext(Dispatchers.IO) { apiClient.getPopularTvShows() }
    }
}