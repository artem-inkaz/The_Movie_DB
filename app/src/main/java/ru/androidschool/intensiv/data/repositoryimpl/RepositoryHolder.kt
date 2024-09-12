package ru.androidschool.intensiv.data.repositoryimpl

import ru.androidschool.intensiv.data.datasource.api.actor.ActorsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.storage.ActorStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.GenreStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.MovieStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.TvShowsStorageRepositoryImpl
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase

object RepositoryHolder {

    private val api = MovieApiClient.apiClient
    private val moviesDB = MoviesDataBase.instance

    private val actorMapper = ActorMapper()
    private val genreMapper = GenreMapper()
    private val movieMapper = MovieStorageMapper()
    private val tvShowsMapper = TvShowsStorageMapper()

    private val repositoryNowPlayingMovie by lazy { MovieNowPlayingNetworkDataSource(api) }
    fun repositoryNowPlayingMovie(): MovieNowPlayingNetworkDataSource = repositoryNowPlayingMovie

    private val repositoryPopularMovie by lazy { MoviePopularNetworkDataSource(api) }
    fun repositoryPopularMovie(): MoviePopularNetworkDataSource = repositoryPopularMovie

    private val repositoryUpCommingMovie by lazy { MovieUpCommingNetworkDataSource(api) }
    fun repositoryUpCommingMovie(): MovieUpCommingNetworkDataSource = repositoryUpCommingMovie

    private val repositoryMovieDetail by lazy { MovieDetailNetworkDataSource(api) }
    fun repositoryMovieDetail(): MovieDetailNetworkDataSource = repositoryMovieDetail

    private val repositoryMovieFromStorage by lazy { MovieStorageRepositoryImpl(moviesDB, movieMapper) }
    fun repositoryMovieFromStorage(): MovieStorageRepositoryImpl = repositoryMovieFromStorage

    private val repositoryMovie by lazy { MovieRepositoryImpl() }
    fun repositoryMovie(): MovieRepositoryImpl = repositoryMovie

    private val repositoryActor by lazy { ActorRepositoryImpl() }
    fun repositoryActor(): ActorRepositoryImpl = repositoryActor

    private val repositoryFromStorageActor by lazy { ActorStorageRepositoryImpl(moviesDB, actorMapper) }
    fun repositoryFromStorageActor(): ActorStorageRepositoryImpl = repositoryFromStorageActor

    private val repositoryActorFromApi by lazy { ActorsNetworkDataSource(api) }
    fun repositoryActorFromApi(): ActorsNetworkDataSource = repositoryActorFromApi

    private val repositoryFromStorageGenre by lazy { GenreStorageRepositoryImpl(moviesDB, genreMapper) }
    fun repositoryFromStorageGenre(): GenreStorageRepositoryImpl = repositoryFromStorageGenre

    private val repositoryGenre by lazy { GenreRepositoryImpl() }
    fun repositoryGenre(): GenreRepositoryImpl = repositoryGenre

    private val repositoryMovieActor by lazy { MovieActorRepositoryImpl() }
    fun repositoryMovieActor(): MovieActorRepositoryImpl = repositoryMovieActor

    private val repositoryMovieGenre by lazy { MovieGenreRepositoryImpl() }
    fun repositoryMovieGenre(): MovieGenreRepositoryImpl = repositoryMovieGenre

    private val repositoryMovies by lazy { MoviesRepositoryImpl() }
    fun repositoryMovies(): MoviesRepositoryImpl = repositoryMovies

    private val repositoryTvShowsFromApi by lazy { TvShowsNetworkDataSource(api) }
    fun repositoryTvShowsFromApi(): TvShowsNetworkDataSource = repositoryTvShowsFromApi

    private val repositoryTvShowsFromStorage by lazy { TvShowsStorageRepositoryImpl(moviesDB, tvShowsMapper) }
    fun repositoryTvShowsFromStorage(): TvShowsStorageRepositoryImpl = repositoryTvShowsFromStorage

    private val repositoryTvShows by lazy {
        TvShowsRepositoryImpl(
            TvShowsNetworkDataSource(api),
            TvShowsStorageRepositoryImpl(moviesDB, tvShowsMapper)
        )
    }

    fun repositoryTvShows(): TvShowsRepositoryImpl = repositoryTvShows
}