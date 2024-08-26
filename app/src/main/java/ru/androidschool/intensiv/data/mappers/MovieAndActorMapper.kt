package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.storage.entities.MovieActorEntity as InData
import ru.androidschool.intensiv.domain.MovieActor as OutData

class MovieAndActorMapper : MapperDomain.Base<InData, OutData> {
    override fun toLocalDataBase(data: OutData): InData = with(data) {
        InData(
            movieId = movieId,
            actorId = actorId
        )
    }

    override fun fromLocalDataBase(data: InData): OutData = with(data) {
        OutData(
            movieId = movieId,
            actorId = actorId
        )
    }
}