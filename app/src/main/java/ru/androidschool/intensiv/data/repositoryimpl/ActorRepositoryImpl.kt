package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.domain.repository.ActorRepository

class ActorRepositoryImpl : ActorRepository {

    private val moviesDB = MoviesDataBase.instance//MovieFinderApp.database
    private val actorMapper = ActorMapper()

    override fun getAll(): Single<List<Actor>> {
        return moviesDB.getActorDao().getAll().map { actorMapper.fromLocalDataBase(it) }
    }

    override fun add(actor: Actor): Completable {
        return moviesDB.getActorDao().add(actorMapper.toLocalDataBase(actor))
    }

    override fun addAll(actors: List<Actor>) {
        return moviesDB.getActorDao().insertAll(actorMapper.toLocalDataBase(actors))
    }

    override fun update(actor: Actor): Completable {
        return moviesDB.getActorDao().update(actorMapper.toLocalDataBase(actor))
    }

    override fun delete(actor: Actor): Completable {
        return moviesDB.getActorDao().delete(actorMapper.toLocalDataBase(actor))
    }
}