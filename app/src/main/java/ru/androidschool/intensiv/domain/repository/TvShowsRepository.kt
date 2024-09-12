package ru.androidschool.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.TvShowsLocal

interface TvShowsRepository {
    fun getAllTvShows(): Observable<List<TvShowsLocal>>
}