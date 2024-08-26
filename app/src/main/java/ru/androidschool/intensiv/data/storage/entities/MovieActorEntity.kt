package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = MovieActorEntity.TABLE_NAME,
    primaryKeys = [MovieActorEntity.MOVIE_ID, MovieActorEntity.ACTOR_ID]
)
data class MovieActorEntity(
    @ColumnInfo(name = MOVIE_ID)
    val movieId: Int,
    @ColumnInfo(name = ACTOR_ID)
    val actorId: Int
) {
    companion object {
        const val TABLE_NAME = "movie_actors"
        const val MOVIE_ID = "movie_id"
        const val ACTOR_ID = "actor_id"
    }
}