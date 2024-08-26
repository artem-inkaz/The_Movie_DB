package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.domain.repository.ActorRepository

class ActorRepositoryImpl: ActorRepository {

    override fun getAll(): Single<List<Actor>> {
        TODO("Not yet implemented")
    }

    override fun add(actor: Actor): Completable {
        TODO("Not yet implemented")
    }

    override fun update(actor: Actor): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(actor: Actor): Completable {
        TODO("Not yet implemented")
    }
}