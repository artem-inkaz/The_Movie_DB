package ru.androidschool.intensiv.core.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = MOVIE_ID)
    val id: Int,
    @ColumnInfo(name = TITLE)
    val title: String? = "",
    @ColumnInfo(name = OVERVIEW)
    val overview: String,
    @ColumnInfo(name = VOTE_AVERAGE)
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = BACKDROP_PATH)
    val backdropPath: String?,
    @ColumnInfo(name = POSTER_PATH)
    val posterPath: String?,
    @ColumnInfo(name = LIKE, defaultValue = "0")
    val like: Boolean = false,
    @ColumnInfo(name = MOVIE_GROUP)
    val movieGroup: String
) {
    companion object {
        const val TABLE_NAME = "movies"
        const val MOVIE_ID = "movie_id"
        const val TITLE = "title"
        const val OVERVIEW = "overview"
        const val VOTE_AVERAGE = "voteAverage"
        const val BACKDROP_PATH = "backdropPath"
        const val POSTER_PATH = "posterPath"
        const val LIKE = "is_favourite"
        const val MOVIE_GROUP = "movie_group"
    }
}
