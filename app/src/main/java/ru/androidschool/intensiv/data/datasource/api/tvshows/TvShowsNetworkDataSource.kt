package ru.androidschool.intensiv.data.datasource.api.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShowsResponse
import javax.inject.Inject

class TvShowsNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    TvShowsDataSource {
    override fun getPopularTvShows(): Single<TvShowsResponse> {
        return apiClient.getPopularTvShows()
    }
}