package ru.androidschool.intensiv.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import javax.inject.Inject

class TvShowsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    suspend operator fun invoke(): Flow<List<TvShowsLocal>> =
        repository.getAllTvShows().flowOn(Dispatchers.IO)
}