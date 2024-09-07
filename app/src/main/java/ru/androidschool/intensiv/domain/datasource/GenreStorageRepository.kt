package ru.androidschool.intensiv.domain.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Genre

interface GenreStorageRepository {
    fun getAll(): Observable<List<Genre>>
    fun add(genre: Genre): Completable
    fun addAll(genre: List<Genre>): Single<List<Genre>>
    fun update(genre: Genre): Completable
    fun delete(genre: Genre): Completable
}