package ru.androidschool.intensiv.core.network.utils

data class MovieParams(
   val page: Int,
   val region: String,
   val minLengthWord: Int,
   val voteAverage: Int,
   val databaseName: String,
   val baseUrl: String,
   val baseImageUrl: String,
   val accessToken: String
)
