package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieAndGenreMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.MovieGenre
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository

class MovieGenreRepositoryImpl : MovieGenreRepository {

    private val moviesDB = MoviesDataBase.instance.getMovieGenreDao()
    private val movieGenreMapper = MovieAndGenreMapper()

    override fun getAll(): Single<List<MovieGenre>> {
        return moviesDB.getAll().map { movieGenreMapper.fromLocalDataBase(it) }
    }

    override fun getByMovieId(moveId: String): Single<List<MovieGenre>> {
        return moviesDB.getAll().map { movieGenreMapper.fromLocalDataBase(it) }
    }

    override fun getByGenreId(genreId: Long): Single<List<MovieGenre>> {
        return moviesDB.getAll().map { movieGenreMapper.fromLocalDataBase(it) }
    }

    override fun add(moveAndGenre: MovieGenre): Completable {
        return moviesDB.add(movieGenreMapper.toLocalDataBase(moveAndGenre))
    }

    override fun addAll(moveAndGenre: List<MovieGenre>) {
        return moviesDB.addAll(movieGenreMapper.toLocalDataBase(moveAndGenre))
    }

    override fun delete(moveAndActor: MovieGenre): Completable {
        return moviesDB.add(movieGenreMapper.toLocalDataBase(moveAndActor))
    }

    override fun delete(moveId: String, actorId: Long): Completable {
        TODO("Not yet implemented")
    }
}