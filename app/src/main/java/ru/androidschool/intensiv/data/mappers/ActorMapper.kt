package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.data.dto.moveidcredits.Cast as InActor
import ru.androidschool.intensiv.data.storage.entities.ActorEntity as InActorStorage
import ru.androidschool.intensiv.data.vo.Actor as OutActor

class ActorMapper : MapperDomain.Base<InActorStorage, OutActor> {
    override fun toLocalDataBase(data: OutActor): InActorStorage = with(data) {
        InActorStorage(
            id = id,
            name = name,
            profilePath = profilePath ?: ""
        )
    }

    override fun toLocalDataBase(dto: Collection<OutActor>): List<InActorStorage> =
        dto.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InActorStorage): OutActor = with(data) {
        OutActor(
            id = id,
            name = name,
            profilePath = profilePath
        )
    }

    override fun fromLocalDataBase(dto: Collection<InActorStorage>): List<OutActor> =
        dto.map { fromLocalDataBase(it) }
}

class ActorMapperDto : MapperDomain.ViewObjectMapper<OutActor, InActor> {

    override fun toViewObject(dto: InActor): OutActor = with(dto) {
        return OutActor(
            id = id,
            name = name,
            profilePath = profilePath,
        )
    }

    override fun toViewObject(dto: Collection<InActor>): List<OutActor> =
        dto.map { toViewObject(it) }
}