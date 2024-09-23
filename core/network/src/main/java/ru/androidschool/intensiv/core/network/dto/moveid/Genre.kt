package ru.androidschool.intensiv.core.network.dto.moveid

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)