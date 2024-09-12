package ru.androidschool.intensiv.data.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowsLocal(
    val id: Int,
    val name: String? = "",
    val voteAverage: Double = 0.0,
    val backdropPath: String?,
    val posterPath: String?
): Parcelable