package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.response.moveidcredits.Cast
import ru.androidschool.intensiv.data.response.movies.Movie
import ru.androidschool.intensiv.data.storage.entities.ActorEntity
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.domain.MovieLocal

private fun toActorDomain(actorEntity: ActorEntity) = Actor(
    id = actorEntity.id,
    name = actorEntity.name,
    profile_path = actorEntity.profile_path
)

private fun toActorEntity(actorDomain: Cast) = ActorEntity(
    id = actorDomain.id,
    name = actorDomain.name,
    profile_path = actorDomain.profilePath?:"",

    )

fun fromApiToMovieDomain(movie: Movie, movieGroup: String, like: Boolean? = false) = MovieLocal(
    id = movie.id,
    title = movie.title,
    overview = movie.overview,
    voteAverage = movie.voteAverage,
    backdropPath = movie.backdropPath,
    posterPath = movie.posterPath,
    like = like ?: false,
    movieGroup = movieGroup
)

private fun fromStorageToMovieDomain(movieEntity: MovieEntity) = MovieLocal(
    id = movieEntity.id.toInt(),
    title = movieEntity.title,
    overview = movieEntity.overview,
    voteAverage = movieEntity.voteAverage,
    backdropPath = movieEntity.backdropPath,
    posterPath = movieEntity.posterPath,
    like = movieEntity.like,
    movieGroup = movieEntity.movieGroup
)

private fun fromApiToMovieEntity(movieDomain: Movie, movieGroup: String) = MovieEntity(
    id = movieDomain.id,
    title = movieDomain.title,
    overview = movieDomain.overview,
    voteAverage = movieDomain.voteAverage,
    backdropPath = movieDomain.backdropPath,
    posterPath = movieDomain.posterPath,
    like = false,
    movieGroup = movieGroup
)