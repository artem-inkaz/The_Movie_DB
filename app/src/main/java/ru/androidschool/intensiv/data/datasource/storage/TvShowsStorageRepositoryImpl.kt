package ru.androidschool.intensiv.data.datasource.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.androidschool.intensiv.core.storage.dao.TvShowsDao
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import javax.inject.Inject

class TvShowsStorageRepositoryImpl @Inject constructor(
    private val dao: TvShowsDao,
    private val mapper: TvShowsStorageMapper
) : TvShowsStorageRepository {

    override suspend fun create(movie: TvShowsLocal) {
        return dao.create(mapper.toLocalDataBase(movie))
    }

    override suspend fun insertAll(movies: List<TvShowsLocal>): Flow<List<TvShowsLocal>> {
        dao.insertAll(mapper.toLocalDataBase(movies))
        return getAllTvShows()

    }

    override suspend fun update(movie: TvShowsLocal) {
        return dao.update(mapper.toLocalDataBase(movie))
    }

    override suspend fun delete(movie: TvShowsLocal) {
        return dao.delete(mapper.toLocalDataBase(movie))
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override fun getAllTvShows(): Flow<List<TvShowsLocal>> {
        return dao.getAllTvShows()
            .map { mapper.fromLocalDataBase(it) }
    }

    override suspend fun getById(id: Int): Flow<TvShowsLocal> {
        return dao.getById(id).map { mapper.fromLocalDataBase(it) }
    }

    override suspend fun search(searchQuery: String): List<TvShowsLocal> {
        return dao.search(searchQuery)
            .map { mapper.fromLocalDataBase(it) }
    }
}