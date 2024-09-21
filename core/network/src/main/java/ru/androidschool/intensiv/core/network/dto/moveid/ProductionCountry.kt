package ru.androidschool.intensiv.core.network.dto.moveid

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)