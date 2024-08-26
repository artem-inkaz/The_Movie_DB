package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.base.MapperDomain
import ru.androidschool.intensiv.domain.Actor as OutActor
import ru.androidschool.intensiv.data.response.moveidcredits.Cast as InActor

class ActorMapper : MapperDomain.ViewObjectMapper<OutActor, InActor> {

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