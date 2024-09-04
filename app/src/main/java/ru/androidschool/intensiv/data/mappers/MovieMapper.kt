package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.storage.entities.MovieEntity as InStorageMovie
import ru.androidschool.intensiv.domain.MovieLocal as OutMovie

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

    override fun toLocalDataBase(dto: Collection<OutMovie>): List<InStorageMovie> =
        dto.map { toLocalDataBase(it) }

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

    override fun fromLocalDataBase(dto: Collection<InStorageMovie>): List<OutMovie> =
        dto.map { fromLocalDataBase(it) }
}