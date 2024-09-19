package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieAndGenreMapper
import ru.androidschool.intensiv.data.storage.dao.MovieGenreDao
import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository
import javax.inject.Inject

class MovieGenreRepositoryImpl @Inject constructor(
    private val dao: MovieGenreDao,
    private val mapper: MovieAndGenreMapper
) : MovieGenreRepository {

    override fun getAll(): Single<List<MovieGenre>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun getByMovieId(moveId: String): Single<List<MovieGenre>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun getByGenreId(genreId: Long): Single<List<MovieGenre>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun add(moveAndGenre: MovieGenre): Completable {
        return dao.add(mapper.toLocalDataBase(moveAndGenre))
    }

    override fun addAll(moveAndGenre: List<MovieGenre>) {
        return dao.addAll(mapper.toLocalDataBase(moveAndGenre))
    }

    override fun delete(moveAndActor: MovieGenre): Completable {
        return dao.add(mapper.toLocalDataBase(moveAndActor))
    }
}