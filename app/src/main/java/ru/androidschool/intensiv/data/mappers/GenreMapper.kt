package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.domain.Genre as OutGenre
import ru.androidschool.intensiv.data.storage.entities.GenreEntity as InGenreStorage

class GenreMapper : MapperDomain.Base<InGenreStorage, OutGenre> {
    override fun toLocalDataBase(data: OutGenre): InGenreStorage = with(data) {
        InGenreStorage(
            id = id,
            name = name,
        )
    }

    override fun toLocalDataBase(dto: Collection<OutGenre>): List<InGenreStorage> =
        dto.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InGenreStorage): OutGenre = with(data) {
        OutGenre(
            id = id,
            name = name,
        )
    }

    override fun fromLocalDataBase(dto: Collection<InGenreStorage>): List<OutGenre> =
        dto.map { fromLocalDataBase(it) }
}