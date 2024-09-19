package ru.androidschool.intensiv.data.datasource.api.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.tvseries.TvShowsResponse
import ru.androidschool.intensiv.data.network.MovieApiInterface
import javax.inject.Inject

class TvShowsNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    TvShowsDataSource {
    override fun getPopularTvShows(): Single<TvShowsResponse> {
        return apiClient.getPopularTvShows()
    }
}