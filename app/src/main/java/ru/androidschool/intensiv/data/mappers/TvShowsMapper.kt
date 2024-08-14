package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.TvShowsLocal as OutTvShows
import ru.androidschool.intensiv.data.tvseries.TvShows as InTvShows

class TvShowsMapper : MapperDomain.ReadOnly<InTvShows, OutTvShows> {
    override fun toView(data: InTvShows): OutTvShows = with(data) {
        OutTvShows(
            name = name,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath
        )
    }
}