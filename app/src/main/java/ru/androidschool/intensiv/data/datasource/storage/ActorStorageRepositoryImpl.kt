package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.Actor
import ru.androidschool.intensiv.domain.datasource.ActorStorageRepository

class ActorStorageRepositoryImpl(
    private val moviesDB: MoviesDataBase,
    private val actorMapper: ActorMapper
) : ActorStorageRepository {

    override fun getAll(): Observable<List<Actor>> {
        return moviesDB.getActorDao().getAll().map { actorMapper.fromLocalDataBase(it) }
    }

    override fun add(actor: Actor): Completable {
        return moviesDB.getActorDao().add(actorMapper.toLocalDataBase(actor))
    }

    override fun addAll(actors: List<Actor>): Single<List<Actor>> =
        moviesDB.getActorDao()
            .insertAll(actorMapper.toLocalDataBase(actors))
            .andThen(getAll().firstOrError())


    override fun update(actor: Actor): Completable {
        return moviesDB.getActorDao().update(actorMapper.toLocalDataBase(actor))
    }

    override fun delete(actor: Actor): Completable {
        return moviesDB.getActorDao().delete(actorMapper.toLocalDataBase(actor))
    }
}