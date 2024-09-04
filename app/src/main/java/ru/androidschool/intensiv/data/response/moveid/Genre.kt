package ru.androidschool.intensiv.data.response.moveid

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)