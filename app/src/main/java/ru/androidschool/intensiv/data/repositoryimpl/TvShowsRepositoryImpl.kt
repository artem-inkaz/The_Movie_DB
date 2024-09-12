package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Observable
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsDataSource
import ru.androidschool.intensiv.data.mappers.TvShowsMapperDto
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import ru.androidschool.intensiv.domain.repository.TvShowsRepository

class TvShowsRepositoryImpl(
    private val api: TvShowsDataSource,
    private val storage: TvShowsStorageRepository
) : TvShowsRepository {

    override fun getAllTvShows(): Observable<List<TvShowsLocal>> =
        Observable.merge(
            storage.getAllTvShows(),
            api.getPopularTvShows()
                .map { response -> response.results.map { TvShowsMapperDto().toViewObject(it) } }
                .flatMap(storage::insertAll)
                .toObservable()
        )
}