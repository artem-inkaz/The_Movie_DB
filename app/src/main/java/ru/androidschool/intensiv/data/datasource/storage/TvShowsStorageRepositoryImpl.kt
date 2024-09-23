package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.core.storage.dao.TvShowsDao
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import javax.inject.Inject

class TvShowsStorageRepositoryImpl @Inject constructor(
    private val dao: TvShowsDao,
    private val mapper: TvShowsStorageMapper
) : TvShowsStorageRepository {

    override fun create(movie: TvShowsLocal): Completable {
        return dao.create(mapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<TvShowsLocal>): Single<List<TvShowsLocal>> {
        return dao
            .insertAll(mapper.toLocalDataBase(movies))
            .andThen(getAllTvShows().firstOrError())
    }

    override fun update(movie: TvShowsLocal): Completable {
        return dao.update(mapper.toLocalDataBase(movie))
    }

    override fun delete(movie: TvShowsLocal) {
        return dao.delete(mapper.toLocalDataBase(movie))
    }

    override fun deleteAll() {
        return dao.deleteAll()
    }

    override fun getAllTvShows(): Observable<List<TvShowsLocal>> {
        return dao.getAllTvShows()
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Observable<TvShowsLocal> {
        return dao.getById(id).map { mapper.fromLocalDataBase(it) }
    }

    override fun search(searchQuery: String): Single<List<TvShowsLocal>> {
        return dao.search(searchQuery)
            .map { mapper.fromLocalDataBase(it) }
    }
}