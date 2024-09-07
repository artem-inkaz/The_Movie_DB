package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.Actor
import ru.androidschool.intensiv.domain.repository.ActorRepository

class ActorRepositoryImpl : ActorRepository {

    override fun getAll(): Observable<List<Actor>> {
        TODO()
    }
}