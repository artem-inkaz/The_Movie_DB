package ru.androidschool.intensiv.data.repositoryimpl

object RepositoryHolder {
    private val repositoryMovie by lazy { MovieRepositoryImpl() }
    fun repositoryMovie(): MovieRepositoryImpl = repositoryMovie

    private val repositoryActor by lazy { ActorRepositoryImpl() }
    fun repositoryActor(): ActorRepositoryImpl = repositoryActor

    private val repositoryGenre by lazy { GenreRepositoryImpl() }
    fun repositoryGenre(): GenreRepositoryImpl = repositoryGenre

    private val repositoryMovieActor by lazy { MovieActorRepositoryImpl() }
    fun repositoryMovieActor(): MovieActorRepositoryImpl = repositoryMovieActor

    private val repositoryMovieGenre by lazy { MovieGenreRepositoryImpl() }
    fun repositoryMovieGenre(): MovieGenreRepositoryImpl = repositoryMovieGenre

    private val repositoryMovies by lazy { MoviesRepositoryImpl() }
    fun repositoryMovies(): MoviesRepositoryImpl = repositoryMovies
}