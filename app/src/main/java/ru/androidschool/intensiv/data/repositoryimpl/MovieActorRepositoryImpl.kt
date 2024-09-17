package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.data.storage.dao.MovieActorDao
import ru.androidschool.intensiv.data.vo.MovieActor
import ru.androidschool.intensiv.domain.repository.MovieActorRepository
import javax.inject.Inject

class MovieActorRepositoryImpl @Inject constructor(
    private val dao: MovieActorDao,
    private val mapper: MovieAndActorMapper
) : MovieActorRepository {

    override fun getAll(): Single<List<MovieActor>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun getByMovieId(moveId: String): Single<List<MovieActor>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun getByActorId(actorId: Int): Single<List<MovieActor>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun add(moveAndActor: MovieActor): Completable {
        return dao.add(mapper.toLocalDataBase(moveAndActor))
    }

    override fun addAll(moveAndActors: List<MovieActor>) {
        return dao.addAll(mapper.toLocalDataBase(moveAndActors))
    }

    override fun delete(moveAndActor: MovieActor): Completable {
        return dao.add(mapper.toLocalDataBase(moveAndActor))
    }

    override fun delete(moveId: String, actorId: Int): Completable {
        TODO("Not yet implemented")
    }
}