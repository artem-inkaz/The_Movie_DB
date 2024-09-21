package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.core.network.dto.moveid.Genre as InGenre
import ru.androidschool.intensiv.data.vo.Genre as OutGenre
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

class GenreMapperDto : MapperDomain.ViewObjectMapper<OutGenre, InGenre> {

    override fun toViewObject(dto: InGenre): OutGenre = with(dto) {
        return OutGenre(
            id = id,
            name = name
        )
    }

    override fun toViewObject(dto: Collection<InGenre>): List<OutGenre> =
        dto.map { toViewObject(it) }
}