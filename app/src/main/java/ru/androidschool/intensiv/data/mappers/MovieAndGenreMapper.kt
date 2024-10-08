package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.core.storage.entities.MovieGenreEntity as InData
import ru.androidschool.intensiv.data.vo.MovieGenre as OutData

class MovieAndGenreMapper : MapperDomain.Base<InData, OutData> {
    override fun toLocalDataBase(data: OutData): InData = with(data) {
        InData(
            movieId = movieId,
            genreId = genreId
        )
    }

    override fun toLocalDataBase(dto: Collection<OutData>): List<InData> =
        dto.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InData): OutData = with(data) {
        OutData(
            movieId = movieId,
            genreId = genreId
        )
    }

    override fun fromLocalDataBase(dto: Collection<InData>): List<OutData> =
        dto.map { fromLocalDataBase(it) }
}