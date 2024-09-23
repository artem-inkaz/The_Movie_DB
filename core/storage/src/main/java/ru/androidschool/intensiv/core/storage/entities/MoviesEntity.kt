package ru.androidschool.intensiv.core.storage.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MoviesEntity(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(
            MovieActorEntity::class,
            parentColumn = "movie_id",
            entityColumn = "actor_id"
        )
    )
    val actorsEntities: List<ActorEntity>?,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(
            MovieGenreEntity::class,
            parentColumn = "movie_id",
            entityColumn = "genre_id"
        )
    )
    val genresEntities: List<GenreEntity>?,
)
