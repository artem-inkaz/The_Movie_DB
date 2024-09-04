package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.domain.TvShowsLocal as OutTvShows
import ru.androidschool.intensiv.data.response.tvseries.TvShows as InTvShows

class TvShowsMapper : MapperDomain.ViewObjectMapper<OutTvShows, InTvShows> {

    override fun toViewObject(dto: InTvShows): OutTvShows = with(dto) {
        return OutTvShows(
                name = name,
                voteAverage = voteAverage,
                backdropPath = backdropPath,
                posterPath = posterPath
            )
    }

    override fun toViewObject(dto: Collection<InTvShows>): List<OutTvShows> =
        dto.map { toViewObject(it) }
}