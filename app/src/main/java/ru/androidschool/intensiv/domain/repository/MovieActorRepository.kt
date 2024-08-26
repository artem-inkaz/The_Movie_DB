package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieActor

interface MovieActorRepository {
    fun getAll(): Single<List<MovieActor>>
    fun getByNoteId(moveId: String): Single<List<MovieActor>>
    fun getByTagId(actorId: Long): Single<List<MovieActor>>
    fun add(moveAndActor: MovieActor): Completable
    fun delete(moveAndActor: MovieActor): Completable
    fun delete(moveId: String, actorId: Long): Completable
}