package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = MovieGenreEntity.TABLE_NAME,
    primaryKeys = [MovieGenreEntity.MOVIE_ID, MovieGenreEntity.GENRE_ID]
)
data class MovieGenreEntity(
    @ColumnInfo(name = MOVIE_ID)
    val movieId: Int,
    @ColumnInfo(name = GENRE_ID)
    val genreId: Int
) {
    companion object {
        const val TABLE_NAME = "movie_genre"
        const val MOVIE_ID = "movie_id"
        const val GENRE_ID = "genre_id"
    }
}
