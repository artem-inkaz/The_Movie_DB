package ru.androidschool.intensiv.data.dto.moveidcredits

data class MoveIdCreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)