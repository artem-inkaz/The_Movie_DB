package ru.androidschool.intensiv.data.moveidcredits

data class MoveIdCreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)