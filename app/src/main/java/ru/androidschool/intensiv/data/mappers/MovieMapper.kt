package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.response.movies.Movie as InMovie
import ru.androidschool.intensiv.data.storage.entities.MovieEntity as InStorageMovie
import ru.androidschool.intensiv.domain.MovieLocal as OutMovie

class MovieMapper : MapperDomain.ViewObjectMapper<OutMovie, InMovie> {
    override fun toViewObject(dto: InMovie): OutMovie = with(dto) {
        return OutMovie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath,
            movieGroup = ""
        )
    }

    override fun toViewObject(dto: Collection<InMovie>): List<OutMovie> =
        dto.map { toViewObject(it) }
}

class MovieStorageMapper : MapperDomain.Base<InStorageMovie, OutMovie> {
    override fun toLocalDataBase(data: OutMovie): InStorageMovie = with(data) {
        InStorageMovie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath,
            like = like,
            movieGroup = movieGroup,
        )
    }

    override fun fromLocalDataBase(data: InStorageMovie): OutMovie = with(data) {
        OutMovie(
            id = id,
            title = title,
            overview = overview,
            voteAverage = voteAverage,
            backdropPath = backdropPath,
            posterPath = posterPath,
            like = like,
            movieGroup = movieGroup,
        )
    }
}