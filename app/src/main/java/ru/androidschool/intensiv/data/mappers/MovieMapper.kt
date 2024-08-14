package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.MovieLocal as OutMovie
import ru.androidschool.intensiv.data.movies.Movie as InMovie

class MovieMapper: MapperDomain.ReadOnly<InMovie, OutMovie> {
    override fun toView(data: InMovie): OutMovie = with(data) {
        OutMovie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath
        )
    }
}