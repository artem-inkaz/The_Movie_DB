package ru.androidschool.intensiv.data.response.movies

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)