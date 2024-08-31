package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieActor

interface MovieActorRepository {
    fun getAll(): Single<List<MovieActor>>
    fun getByMovieId(moveId: String): Single<List<MovieActor>>
    fun getByActorId(actorId: Int): Single<List<MovieActor>>
    fun add(moveAndActor: MovieActor): Completable
    fun addAll(moveAndActors: List<MovieActor>)
    fun delete(moveAndActor: MovieActor): Completable
    fun delete(moveId: String, actorId: Int): Completable
}