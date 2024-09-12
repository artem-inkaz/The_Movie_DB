package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository

class TvShowsStorageRepositoryImpl(
    private val moviesDB: MoviesDataBase,
    private val mapper: TvShowsStorageMapper
) : TvShowsStorageRepository {

    override fun create(movie: TvShowsLocal): Completable {
        return moviesDB.getTvShowsDao().create(mapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<TvShowsLocal>): Single<List<TvShowsLocal>> {
        return moviesDB.getTvShowsDao()
            .insertAll(mapper.toLocalDataBase(movies))
            .andThen(getAllTvShows().firstOrError())
    }

    override fun update(movie: TvShowsLocal): Completable {
        return moviesDB.getTvShowsDao().update(mapper.toLocalDataBase(movie))
    }

    override fun delete(movie: TvShowsLocal) {
        return moviesDB.getTvShowsDao().delete(mapper.toLocalDataBase(movie))
    }

    override fun deleteAll() {
        return moviesDB.getTvShowsDao().deleteAll()
    }

    override fun getAllTvShows(): Observable<List<TvShowsLocal>> {
        return moviesDB.getTvShowsDao().getAllTvShows()
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Observable<TvShowsLocal> {
        return moviesDB.getTvShowsDao().getById(id).map { mapper.fromLocalDataBase(it) }
    }

    override fun search(searchQuery: String): Single<List<TvShowsLocal>> {
        return moviesDB.getTvShowsDao().search(searchQuery)
            .map { mapper.fromLocalDataBase(it) }
    }
}