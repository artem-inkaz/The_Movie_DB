package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieActor
import ru.androidschool.intensiv.domain.repository.MovieActorRepository

class MovieActorRepositoryImpl: MovieActorRepository {
    override fun getAll(): Single<List<MovieActor>> {
        TODO("Not yet implemented")
    }

    override fun getByNoteId(moveId: String): Single<List<MovieActor>> {
        TODO("Not yet implemented")
    }

    override fun getByTagId(actorId: Long): Single<List<MovieActor>> {
        TODO("Not yet implemented")
    }

    override fun add(moveAndActor: MovieActor): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(moveAndActor: MovieActor): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(moveId: String, actorId: Long): Completable {
        TODO("Not yet implemented")
    }
}