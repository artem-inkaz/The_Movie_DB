package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import ru.androidschool.intensiv.domain.usecase.FeedUseCase
import ru.androidschool.intensiv.domain.usecase.MovieGenreUseCase
import ru.androidschool.intensiv.domain.usecase.MovieStorageUseCase
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import javax.inject.Singleton

@Module
class UseCaseModule {

    @ApplicationScope
    @Provides
    fun provideFeedUseCase(
        nowPlayingMovie: MovieNowPlayingNetworkDataSource,
        popularMovie: MoviePopularNetworkDataSource,
        upCommingMovie: MovieUpCommingNetworkDataSource
    ): FeedUseCase {
        return FeedUseCase(nowPlayingMovie, popularMovie, upCommingMovie)
    }

    @ApplicationScope
    @Provides
    fun provideMovieGenreUseCase(
        repository: MovieGenreRepository
    ): MovieGenreUseCase {
        return MovieGenreUseCase(repository)
    }

    @ApplicationScope
    @Provides
    fun provideMovieStorageUseCase(
        repository: MovieStorageRepository
    ): MovieStorageUseCase {
        return MovieStorageUseCase(repository)
    }

    @ApplicationScope
    @Provides
    fun provideTvShowsUseCase(
        repository: TvShowsRepository
    ): TvShowsUseCase {
        return TvShowsUseCase(repository)
    }
}