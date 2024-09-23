package ru.androidschool.intensiv.core.network.utils

fun voteAverage(voteAverage: Double): Float {
    return voteAverage.div(movieParams.voteAverage).toFloat()
}