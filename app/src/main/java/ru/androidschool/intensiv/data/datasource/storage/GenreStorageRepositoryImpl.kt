package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.Genre
import ru.androidschool.intensiv.domain.datasource.GenreStorageRepository

class GenreStorageRepositoryImpl(
    private val moviesDB: MoviesDataBase,
    private val genreMapper: GenreMapper
) : GenreStorageRepository {

    override fun getAll(): Observable<List<Genre>> {
        return moviesDB.getGenreDao().getAll().map { genreMapper.fromLocalDataBase(it) }
    }

    override fun add(genre: Genre): Completable {
        return moviesDB.getGenreDao().add(genreMapper.toLocalDataBase(genre))
    }

    override fun addAll(genre: List<Genre>): Single<List<Genre>> {
        return moviesDB.getGenreDao()
            .addAll(genreMapper.toLocalDataBase(genre))
            .andThen(getAll().firstOrError())
    }

    override fun update(genre: Genre): Completable {
        return moviesDB.getGenreDao().update(genreMapper.toLocalDataBase(genre))
    }

    override fun delete(genre: Genre): Completable {
        return moviesDB.getGenreDao().delete(genreMapper.toLocalDataBase(genre))
    }
}