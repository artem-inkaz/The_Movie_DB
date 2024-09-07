package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Observable
import ru.androidschool.intensiv.data.vo.Genre
import ru.androidschool.intensiv.domain.repository.GenreRepository

class GenreRepositoryImpl : GenreRepository {

    override fun getAll(): Observable<List<Genre>> {
        TODO()
    }
}