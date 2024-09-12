package ru.androidschool.intensiv.data.vo

data class Movies(
    val movie: MovieLocal,
    val actors: List<Actor>?,
    val genres: List<Genre>?,
)
