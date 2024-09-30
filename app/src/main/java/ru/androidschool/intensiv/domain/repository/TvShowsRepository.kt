package ru.androidschool.intensiv.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.vo.TvShowsLocal

interface TvShowsRepository {
    suspend fun getAllTvShows(): Flow<List<TvShowsLocal>>
}