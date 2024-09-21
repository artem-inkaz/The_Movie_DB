package ru.androidschool.intensiv.data.dto.moveidcredits

import ru.androidschool.intensiv.core.network.dto.moveidcredits.Cast
import ru.androidschool.intensiv.core.network.dto.moveidcredits.Crew

data class MoveIdCreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)