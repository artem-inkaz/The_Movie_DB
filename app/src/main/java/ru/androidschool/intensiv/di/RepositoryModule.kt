package ru.androidschool.intensiv.di

import dagger.Binds
import dagger.Module
import ru.androidschool.intensiv.data.datasource.api.actor.ActorsDataSource
import ru.androidschool.intensiv.data.datasource.api.actor.ActorsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.search.SearchMovieDataSource
import ru.androidschool.intensiv.data.datasource.api.search.SearchMovieNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsDataSource
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.storage.ActorStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.GenreStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.MovieStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.TvShowsStorageRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.MovieActorRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.MovieGenreRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.MovieRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.TvShowsRepositoryImpl
import ru.androidschool.intensiv.domain.datasource.ActorStorageRepository
import ru.androidschool.intensiv.domain.datasource.GenreStorageRepository
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import ru.androidschool.intensiv.domain.repository.MovieActorRepository
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @ApplicationScope
    @Binds
    fun bindsMovieActorRepository(
        repository: MovieActorRepositoryImpl
    ): MovieActorRepository

    @ApplicationScope
    @Binds
    fun bindsMovieGenreRepository(
        repository: MovieGenreRepositoryImpl
    ): MovieGenreRepository

    @ApplicationScope
    @Binds
    fun bindsMovieRepository(
        repository: MovieRepositoryImpl
    ): MovieRepository

    @ApplicationScope
    @Binds
    fun bindsTvShowsRepository(
        repository: TvShowsRepositoryImpl
    ): TvShowsRepository

    @ApplicationScope
    @Binds
    fun bindsActorsDataSource(
        dataSource: ActorsNetworkDataSource
    ): ActorsDataSource

    @ApplicationScope
    @Binds
    fun bindsNowPlayingMovieDataSource(
        dataSource: MovieNowPlayingNetworkDataSource
    ): MovieDataSource

    @ApplicationScope
    @Binds
    fun bindsPopularMovieDataSource(
        dataSource: MoviePopularNetworkDataSource
    ): MovieDataSource

    @ApplicationScope
    @Binds
    fun bindsUpCommingMovieDataSource(
        dataSource: MovieUpCommingNetworkDataSource
    ): MovieDataSource

    @ApplicationScope
    @Binds
    fun bindsActorStorageRepository(
        repository: ActorStorageRepositoryImpl
    ): ActorStorageRepository

    @ApplicationScope
    @Binds
    fun bindsGenreStorageRepository(
        repository: GenreStorageRepositoryImpl
    ): GenreStorageRepository

    @ApplicationScope
    @Binds
    fun bindsMovieStorageRepository(
        repository: MovieStorageRepositoryImpl
    ): MovieStorageRepository

    @ApplicationScope
    @Binds
    fun bindsTvShowsStorageRepository(
        repository: TvShowsStorageRepositoryImpl
    ): TvShowsStorageRepository

    @ApplicationScope
    @Binds
    fun bindsTvShowsDataSource(
        dataSource: TvShowsNetworkDataSource
    ): TvShowsDataSource

    @ApplicationScope
    @Binds
    fun bindsMovieDetailDataSource(
        dataSource: MovieDetailNetworkDataSource
    ): MovieDetailDataSource

    @ApplicationScope
    @Binds
    fun bindsSearchMovieDataSource(
        dataSource: SearchMovieNetworkDataSource
    ): SearchMovieDataSource
}