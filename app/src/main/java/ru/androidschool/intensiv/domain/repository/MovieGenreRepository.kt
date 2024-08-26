package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieGenre

interface MovieGenreRepository {
    fun getAll(): Single<List<MovieGenre>>
    fun getByNoteId(moveId: String): Single<List<MovieGenre>>
    fun getByTagId(genreId: Long): Single<List<MovieGenre>>
    fun add(moveAndActor: MovieGenre): Completable
    fun delete(moveAndActor: MovieGenre): Completable
    fun delete(moveId: String, actorId: Long): Completable
}