package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.Genre
import ru.androidschool.intensiv.domain.repository.GenreRepository

class GenreRepositoryImpl: GenreRepository {
    override fun getAll(): Single<List<Genre>> {
        TODO("Not yet implemented")
    }

    override fun add(genre: Genre): Completable {
        TODO("Not yet implemented")
    }

    override fun update(genre: Genre): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(genre: Genre): Completable {
        TODO("Not yet implemented")
    }
}