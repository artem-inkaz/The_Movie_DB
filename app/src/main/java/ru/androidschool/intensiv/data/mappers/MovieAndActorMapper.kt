package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.core.storage.entities.MovieActorEntity as InData
import ru.androidschool.intensiv.data.vo.MovieActor as OutData

class MovieAndActorMapper : MapperDomain.Base<InData, OutData> {
    override fun toLocalDataBase(data: OutData): InData = with(data) {
        InData(
            movieId = movieId,
            actorId = actorId
        )
    }

    override fun toLocalDataBase(dto: Collection<OutData>): List<InData> =
        dto.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InData): OutData = with(data) {
        OutData(
            movieId = movieId,
            actorId = actorId
        )
    }

    override fun fromLocalDataBase(dto: Collection<InData>): List<OutData> =
        dto.map { fromLocalDataBase(it) }
}