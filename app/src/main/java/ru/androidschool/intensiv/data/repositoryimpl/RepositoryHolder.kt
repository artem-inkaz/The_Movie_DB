package ru.androidschool.intensiv.data.repositoryimpl

import ru.androidschool.intensiv.data.datasource.api.actor.ActorFromApiImpl
import ru.androidschool.intensiv.data.datasource.api.movie.MovieFromApiImpl
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsFromApiImpl
import ru.androidschool.intensiv.data.datasource.storage.ActorStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.GenreStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.MovieStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.TvShowsStorageRepositoryImpl

object RepositoryHolder {
    private val repositoryMovieFromApi by lazy { MovieFromApiImpl() }
    fun repositoryMovieFromApi(): MovieFromApiImpl = repositoryMovieFromApi

    private val repositoryMovieFromStorage by lazy { MovieStorageRepositoryImpl() }
    fun repositoryMovieFromStorage(): MovieStorageRepositoryImpl = repositoryMovieFromStorage

    private val repositoryMovie by lazy { MovieRepositoryImpl() }
    fun repositoryMovie(): MovieRepositoryImpl = repositoryMovie

    private val repositoryActor by lazy { ActorRepositoryImpl() }
    fun repositoryActor(): ActorRepositoryImpl = repositoryActor

    private val repositoryFromStorageActor by lazy { ActorStorageRepositoryImpl() }
    fun repositoryFromStorageActor(): ActorStorageRepositoryImpl = repositoryFromStorageActor

    private val repositoryActorFromApi by lazy { ActorFromApiImpl() }
    fun repositoryActorFromApi(): ActorFromApiImpl = repositoryActorFromApi

    private val repositoryFromStorageGenre by lazy { GenreStorageRepositoryImpl() }
    fun repositoryFromStorageGenre(): GenreStorageRepositoryImpl = repositoryFromStorageGenre

    private val repositoryGenre by lazy { GenreRepositoryImpl() }
    fun repositoryGenre(): GenreRepositoryImpl = repositoryGenre

    private val repositoryMovieActor by lazy { MovieActorRepositoryImpl() }
    fun repositoryMovieActor(): MovieActorRepositoryImpl = repositoryMovieActor

    private val repositoryMovieGenre by lazy { MovieGenreRepositoryImpl() }
    fun repositoryMovieGenre(): MovieGenreRepositoryImpl = repositoryMovieGenre

    private val repositoryMovies by lazy { MoviesRepositoryImpl() }
    fun repositoryMovies(): MoviesRepositoryImpl = repositoryMovies

    private val repositoryTvShowsFromApi by lazy { TvShowsFromApiImpl() }
    fun repositoryTvShowsFromApi(): TvShowsFromApiImpl = repositoryTvShowsFromApi

    private val repositoryTvShowsFromStorage by lazy { TvShowsStorageRepositoryImpl() }
    fun repositoryTvShowsFromStorage(): TvShowsStorageRepositoryImpl = repositoryTvShowsFromStorage

    private val repositoryTvShows by lazy {
        TvShowsRepositoryImpl(
            TvShowsFromApiImpl(),
            TvShowsStorageRepositoryImpl()
        )
    }

    fun repositoryTvShows(): TvShowsRepositoryImpl = repositoryTvShows
}