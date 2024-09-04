package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.Genre
import ru.androidschool.intensiv.domain.repository.GenreRepository

class GenreRepositoryImpl : GenreRepository {

    private val moviesDB = MoviesDataBase.instance.getGenreDao()
    private val genreMapper = GenreMapper()

    override fun getAll(): Single<List<Genre>> {
        return moviesDB.getAll().map { genreMapper.fromLocalDataBase(it) }
    }

    override fun add(genre: Genre) {
        return moviesDB.add(genreMapper.toLocalDataBase(genre))
    }

    override fun addAll(genre: List<Genre>) {
        return moviesDB.addAll(genreMapper.toLocalDataBase(genre))
    }

    override fun update(genre: Genre): Completable {
        return moviesDB.update(genreMapper.toLocalDataBase(genre))
    }

    override fun delete(genre: Genre): Completable {
        return moviesDB.delete(genreMapper.toLocalDataBase(genre))
    }
}