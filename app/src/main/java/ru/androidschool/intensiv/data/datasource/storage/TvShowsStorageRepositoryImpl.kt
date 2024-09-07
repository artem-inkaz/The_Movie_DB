package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository

class TvShowsStorageRepositoryImpl : TvShowsStorageRepository {

    private val moviesDB = MoviesDataBase.instance.getTvShowsDao()
    private val mapper = TvShowsStorageMapper()

    override fun create(movie: TvShowsLocal): Completable {
        return moviesDB.create(mapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<TvShowsLocal>): Single<List<TvShowsLocal>> {
        return moviesDB
            .insertAll(mapper.toLocalDataBase(movies))
            .andThen(getAllTvShows().firstOrError())
    }

    override fun update(movie: TvShowsLocal): Completable {
        return moviesDB.update(mapper.toLocalDataBase(movie))
    }

    override fun delete(movie: TvShowsLocal) {
        return moviesDB.delete(mapper.toLocalDataBase(movie))
    }

    override fun deleteAll() { return moviesDB.deleteAll() }

    override fun getAllTvShows(): Observable<List<TvShowsLocal>> {
        return moviesDB.getAllTvShows()
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Observable<TvShowsLocal> {
        return moviesDB.getById(id).map { mapper.fromLocalDataBase(it) }
    }

    override fun search(searchQuery: String): Single<List<TvShowsLocal>> {
        return moviesDB.search(searchQuery)
            .map { mapper.fromLocalDataBase(it) }
    }
}