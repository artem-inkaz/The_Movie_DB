package ru.androidschool.intensiv.domain.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.TvShowsLocal

interface TvShowsStorageRepository {
    fun create(movie: TvShowsLocal): Completable
    fun insertAll(movie: List<TvShowsLocal>): Single<List<TvShowsLocal>>
    fun update(movie: TvShowsLocal): Completable
    fun delete(movie: TvShowsLocal)
    fun deleteAll()
    fun getAllTvShows(): Observable<List<TvShowsLocal>>
    fun getById(id: Int): Observable<TvShowsLocal>
    fun search(searchQuery: String): Single<List<TvShowsLocal>>
}