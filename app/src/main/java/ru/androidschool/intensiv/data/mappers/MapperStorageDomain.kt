package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.response.moveidcredits.Cast
import ru.androidschool.intensiv.data.response.movies.Movie
import ru.androidschool.intensiv.data.storage.entities.ActorEntity
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.domain.MovieGenre
import ru.androidschool.intensiv.domain.MovieLocal

private fun toActorDomain(actorEntity: ActorEntity) = Actor(
    id = actorEntity.id,
    name = actorEntity.name,
    profile_path = actorEntity.profile_path
)

private fun toActorEntity(actorDomain: Cast) = ActorEntity(
    id = actorDomain.id,
    name = actorDomain.name,
    profile_path = actorDomain.profilePath ?: "",
)

fun fromApiToMovieDomain(movieApi: Movie, movieGroup: String, like: Boolean? = false) = MovieLocal(
    id = movieApi.id,
    title = movieApi.title,
    overview = movieApi.overview,
    voteAverage = movieApi.voteAverage,
    backdropPath = movieApi.backdropPath,
    posterPath = movieApi.posterPath,
    like = like ?: false,
    movieGroup = movieGroup
)

fun fromApiToMovieGenreDomain(genreId: Int, moveId: Int) = MovieGenre(
    movieId = moveId,
    genreId = genreId
)

private fun fromStorageToMovieDomain(movieEntity: MovieEntity) = MovieLocal(
    id = movieEntity.id,
    title = movieEntity.title,
    overview = movieEntity.overview,
    voteAverage = movieEntity.voteAverage,
    backdropPath = movieEntity.backdropPath,
    posterPath = movieEntity.posterPath,
    like = movieEntity.like,
    movieGroup = movieEntity.movieGroup
)

private fun fromApiToMovieEntity(movieApi: Movie, movieGroup: String) = MovieEntity(
    id = movieApi.id,
    title = movieApi.title,
    overview = movieApi.overview,
    voteAverage = movieApi.voteAverage,
    backdropPath = movieApi.backdropPath,
    posterPath = movieApi.posterPath,
    like = false,
    movieGroup = movieGroup
)