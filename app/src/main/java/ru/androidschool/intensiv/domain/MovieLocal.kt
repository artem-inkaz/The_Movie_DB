package ru.androidschool.intensiv.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieLocal(
    val id: Int,
    val title: String? = "",
    val overview: String,
    val voteAverage: Double = 0.0,
    val backdropPath: String?,
    val posterPath: String?,
    val like: Boolean = false,
    val movieGroup: String
): Parcelable