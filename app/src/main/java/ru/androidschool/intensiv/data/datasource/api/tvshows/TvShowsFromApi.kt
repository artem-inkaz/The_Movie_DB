package ru.androidschool.intensiv.data.datasource.api.tvshows

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.movies.MoviesResponse
import ru.androidschool.intensiv.data.dto.tvseries.TvShowsResponse

interface TvShowsFromApi {
    fun getPopularTvShows(): Single<TvShowsResponse>
}