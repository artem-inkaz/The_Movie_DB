package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.core.storage.dao.GenreDao
import ru.androidschool.intensiv.data.vo.Genre
import ru.androidschool.intensiv.domain.datasource.GenreStorageRepository
import javax.inject.Inject

class GenreStorageRepositoryImpl @Inject constructor(
    private val dao: GenreDao,
    private val mapper: GenreMapper
) : GenreStorageRepository {

    override fun getAll(): Observable<List<Genre>> {
        return dao.getAll().map { mapper.fromLocalDataBase(it) }
    }

    override fun add(genre: Genre): Completable {
        return dao.add(mapper.toLocalDataBase(genre))
    }

    override fun addAll(genre: List<Genre>): Single<List<Genre>> {
        return dao
            .addAll(mapper.toLocalDataBase(genre))
            .andThen(getAll().firstOrError())
    }

    override fun update(genre: Genre): Completable {
        return dao.update(mapper.toLocalDataBase(genre))
    }

    override fun delete(genre: Genre): Completable {
        return dao.delete(mapper.toLocalDataBase(genre))
    }
}