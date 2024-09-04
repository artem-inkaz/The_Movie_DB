package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = MovieGenreEntity.TABLE_NAME,
)
data class MovieGenreEntity(
    @PrimaryKey(autoGenerate = false)
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

data class GenreWithMovie(
    @Embedded val genre: GenreEntity,
    @Relation(
        parentColumn = GenreEntity.GENRE_ID,
        entityColumn = MovieGenreEntity.GENRE_ID
    )
    val movie: List<MovieGenreEntity>,
)