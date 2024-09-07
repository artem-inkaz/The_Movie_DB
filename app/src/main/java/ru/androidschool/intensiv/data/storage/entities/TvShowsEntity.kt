package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TvShowsEntity.TABLE_NAME)
data class TvShowsEntity(
    @PrimaryKey
    @ColumnInfo(name = TV_SHOWS_ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String? = "",
    @ColumnInfo(name = VOTE_AVERAGE)
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = BACKDROP_PATH)
    val backdropPath: String?,
    @ColumnInfo(name = POSTER_PATH)
    val posterPath: String?
) {
    companion object {
        const val TABLE_NAME = "tv_shows"
        const val TV_SHOWS_ID = "tv_shows_id"
        const val NAME = "name"
        const val POSTER_PATH = "poster_path"
        const val BACKDROP_PATH = "backdrop_path"
        const val VOTE_AVERAGE = "vote_average"
    }
}

