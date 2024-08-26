package ru.androidschool.intensiv.domain

data class Actor(
    val id: Int,
    val name: String,
    val profile_path: String? = null
)