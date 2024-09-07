package ru.androidschool.intensiv.domain.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Actor

interface ActorStorageRepository {
    fun getAll(): Observable<List<Actor>>
    fun add(actor: Actor): Completable
    fun addAll(movie: List<Actor>): Single<List<Actor>>
    fun update(actor: Actor): Completable
    fun delete(actor: Actor): Completable
}