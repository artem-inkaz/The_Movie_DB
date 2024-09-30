package ru.androidschool.intensiv.domain.datasource

import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.vo.TvShowsLocal

interface TvShowsStorageRepository {
    suspend fun create(movie: TvShowsLocal)
    suspend fun insertAll(movie: List<TvShowsLocal>): Flow<List<TvShowsLocal>>
    suspend fun update(movie: TvShowsLocal)
    suspend fun delete(movie: TvShowsLocal)
    suspend fun deleteAll()
    fun getAllTvShows(): Flow<List<TvShowsLocal>>
    suspend fun getById(id: Int): Flow<TvShowsLocal>
    suspend fun search(searchQuery: String): List<TvShowsLocal>
}