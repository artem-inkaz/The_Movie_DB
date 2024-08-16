package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.MovieLocal as OutMovie
import ru.androidschool.intensiv.data.movies.Movie as InMovie

class MovieMapper : MapperDomain.ViewObjectMapper<OutMovie, InMovie> {

    override fun toViewObject(dto: InMovie): OutMovie = with(dto) {
        return OutMovie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath
        )
    }

    override fun toViewObject(dto: Collection<InMovie>): List<OutMovie> =
        dto.map { toViewObject(it) }
}