package ru.androidschool.intensiv.data.datasource.api.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.tvseries.TvShowsResponse
import ru.androidschool.intensiv.data.network.MovieApiClient

class TvShowsFromApiImpl : TvShowsFromApi {
    override fun getPopularTvShows(): Single<TvShowsResponse> {
        return MovieApiClient.apiClient.getPopularTvShows()
    }
}