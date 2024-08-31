package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.Genre

interface GenreRepository {
    fun getAll(): Single<List<Genre>>
    fun add(genre: Genre)
    fun addAll(genre: List<Genre>)
    fun update(genre: Genre): Completable
    fun delete(genre: Genre): Completable
}