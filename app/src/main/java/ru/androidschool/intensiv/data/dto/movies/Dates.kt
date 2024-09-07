package ru.androidschool.intensiv.data.dto.movies

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)