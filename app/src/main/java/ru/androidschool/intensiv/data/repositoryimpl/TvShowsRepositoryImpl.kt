package ru.androidschool.intensiv.data.repositoryimpl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsDataSource
import ru.androidschool.intensiv.data.mappers.TvShowsMapperDto
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val api: TvShowsDataSource,
    private val storage: TvShowsStorageRepository
) : TvShowsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllTvShows(): Flow<List<TvShowsLocal>> =
        merge(
            storage.getAllTvShows(),
            flowOf(api.getPopularTvShows())
                .map { response -> response.results.map { TvShowsMapperDto().toViewObject(it) } }
                .flatMapConcat(storage::insertAll)
        )
}