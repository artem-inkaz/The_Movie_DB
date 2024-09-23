package ru.androidschool.intensiv.core.network.dto.movies

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)