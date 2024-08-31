package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.Actor

interface ActorRepository {
    fun getAll(): Single<List<Actor>>
    fun add(actor: Actor): Completable
    fun addAll(movie: List<Actor>)
    fun update(actor: Actor): Completable
    fun delete(actor: Actor): Completable
}