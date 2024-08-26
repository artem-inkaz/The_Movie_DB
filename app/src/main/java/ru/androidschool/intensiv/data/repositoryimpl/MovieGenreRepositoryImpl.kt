package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieGenre
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository

class MovieGenreRepositoryImpl: MovieGenreRepository {
    override fun getAll(): Single<List<MovieGenre>> {
        TODO("Not yet implemented")
    }

    override fun getByNoteId(moveId: String): Single<List<MovieGenre>> {
        TODO("Not yet implemented")
    }

    override fun getByTagId(genreId: Long): Single<List<MovieGenre>> {
        TODO("Not yet implemented")
    }

    override fun add(moveAndActor: MovieGenre): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(moveAndActor: MovieGenre): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(moveId: String, actorId: Long): Completable {
        TODO("Not yet implemented")
    }
}