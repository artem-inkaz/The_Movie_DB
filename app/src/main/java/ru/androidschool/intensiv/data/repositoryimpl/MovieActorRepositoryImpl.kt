package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.MovieActor
import ru.androidschool.intensiv.domain.repository.MovieActorRepository

class MovieActorRepositoryImpl: MovieActorRepository {

    private val moviesDB = MoviesDataBase.instance//MovieFinderApp.database
    private val movieActorMapper = MovieAndActorMapper()

    override fun getAll(): Single<List<MovieActor>> {
        return moviesDB.getMovieActorDao().getAll().map { movieActorMapper.fromLocalDataBase(it) }
    }

    override fun getByMovieId(moveId: String): Single<List<MovieActor>> {
        return moviesDB.getMovieActorDao().getAll().map { movieActorMapper.fromLocalDataBase(it) }
    }

    override fun getByActorId(actorId: Int): Single<List<MovieActor>> {
        return moviesDB.getMovieActorDao().getAll().map { movieActorMapper.fromLocalDataBase(it) }
    }

    override fun add(moveAndActor: MovieActor): Completable {
        return moviesDB.getMovieActorDao().add(movieActorMapper.toLocalDataBase(moveAndActor))
    }

    override fun addAll(moveAndActors: List<MovieActor>) {
        return moviesDB.getMovieActorDao().addAll(movieActorMapper.toLocalDataBase(moveAndActors))
    }

    override fun delete(moveAndActor: MovieActor): Completable {
        return moviesDB.getMovieActorDao().add(movieActorMapper.toLocalDataBase(moveAndActor))
    }

    override fun delete(moveId: String, actorId: Int): Completable {
        TODO("Not yet implemented")
    }
}