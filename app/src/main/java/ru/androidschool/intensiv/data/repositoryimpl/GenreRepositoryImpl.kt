package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.Genre
import ru.androidschool.intensiv.domain.repository.GenreRepository

class GenreRepositoryImpl : GenreRepository {

    private val moviesDB = MoviesDataBase.instance//MovieFinderApp.database
    private val genreMapper = GenreMapper()

    override fun getAll(): Single<List<Genre>> {
        return moviesDB.getGenreDao().getAll().map { genreMapper.fromLocalDataBase(it) }
    }

    override fun add(genre: Genre) {
        return moviesDB.getGenreDao().add(genreMapper.toLocalDataBase(genre))
    }

    override fun addAll(genre: List<Genre>) {
        return moviesDB.getGenreDao().addAll(genreMapper.toLocalDataBase(genre))
    }

    override fun update(genre: Genre): Completable {
        return moviesDB.getGenreDao().update(genreMapper.toLocalDataBase(genre))
    }

    override fun delete(genre: Genre): Completable {
        return moviesDB.getGenreDao().delete(genreMapper.toLocalDataBase(genre))
    }
}