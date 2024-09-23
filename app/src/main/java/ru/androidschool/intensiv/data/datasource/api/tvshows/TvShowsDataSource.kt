package ru.androidschool.intensiv.data.datasource.api.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShowsResponse

interface TvShowsDataSource {
    fun getPopularTvShows(): Single<TvShowsResponse>
}