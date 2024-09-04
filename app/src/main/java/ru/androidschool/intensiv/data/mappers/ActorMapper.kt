package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.data.response.moveidcredits.Cast as InActor
import ru.androidschool.intensiv.data.storage.entities.ActorEntity as InActorStorage
import ru.androidschool.intensiv.domain.Actor as OutActor

class ActorMapper : MapperDomain.Base<InActorStorage, OutActor> {
    override fun toLocalDataBase(data: OutActor): InActorStorage = with(data) {
        InActorStorage(
            id = id,
            name = name,
            profile_path = profile_path ?: ""
        )
    }

    override fun toLocalDataBase(dto: Collection<OutActor>): List<InActorStorage> =
        dto.map { toLocalDataBase(it) }

    override fun fromLocalDataBase(data: InActorStorage): OutActor = with(data) {
        OutActor(
            id = id,
            name = name,
            profile_path = profile_path
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
            profile_path = profilePath,
        )
    }

    override fun toViewObject(dto: Collection<InActor>): List<OutActor> =
        dto.map { toViewObject(it) }
}