package ru.androidschool.intensiv.data.storage.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MoviesEntity(
    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(
            MovieActorEntity::class,
            parentColumn = "movie_id",
            entityColumn = "actor_id"
        )
    )
    val actors: List<ActorEntity>,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(
            MovieGenreEntity::class,
            parentColumn = "movie_id",
            entityColumn = "genre_id"
        )
    )
    val genres: List<GenreEntity>,
)
