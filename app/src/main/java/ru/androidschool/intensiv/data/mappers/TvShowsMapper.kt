package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShows as InTvShows
import ru.androidschool.intensiv.core.storage.entities.TvShowsEntity as InStorageTvShows
import ru.androidschool.intensiv.data.vo.TvShowsLocal as OutTvShows

class TvShowsStorageMapper : MapperDomain.Base<InStorageTvShows, OutTvShows>{
    override fun toLocalDataBase(data: OutTvShows): InStorageTvShows = with(data) {
        InStorageTvShows(
            id = id,
            name = name,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath
        )
    }

    override fun toLocalDataBase(data: Collection<OutTvShows>): List<InStorageTvShows> =
        data.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InStorageTvShows): OutTvShows = with(data){
        OutTvShows(
            id = id,
            name = name,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath
        )
    }

    override fun fromLocalDataBase(data: Collection<InStorageTvShows>): List<OutTvShows> =
        data.map { fromLocalDataBase(it) }
}

class TvShowsMapperDto : MapperDomain.ViewObjectMapper<OutTvShows, InTvShows> {

    override fun toViewObject(dto: InTvShows): OutTvShows = with(dto) {
        return OutTvShows(
            id = id,
                name = name,
                voteAverage = voteAverage,
                backdropPath = backdropPath,
                posterPath = posterPath
            )
    }

    override fun toViewObject(dto: Collection<InTvShows>): List<OutTvShows> =
        dto.map { toViewObject(it) }
}