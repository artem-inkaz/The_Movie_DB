package ru.androidschool.intensiv.domain

data class Movies(
    val movie: MovieLocal,
    val actors: List<Actor>,
    val genres: List<Genre>,
)
