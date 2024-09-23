package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.core.storage.dao.ActorDao
import ru.androidschool.intensiv.data.vo.Actor
import ru.androidschool.intensiv.domain.datasource.ActorStorageRepository
import javax.inject.Inject

class ActorStorageRepositoryImpl @Inject constructor(
    private val dao: ActorDao,
    private val mapper: ActorMapper
) : ActorStorageRepository {

    override fun getAll(): Observable<List<Actor>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun add(actor: Actor): Completable {
        return dao.add(mapper.toLocalDataBase(actor))
    }

    override fun addAll(actors: List<Actor>): Single<List<Actor>> =
        dao
            .insertAll(mapper.toLocalDataBase(actors))
            .andThen(getAll().firstOrError())


    override fun update(actor: Actor): Completable {
        return dao.update(mapper.toLocalDataBase(actor))
    }

    override fun delete(actor: Actor): Completable {
        return dao.delete(mapper.toLocalDataBase(actor))
    }
}