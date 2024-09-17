package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.mappers.GenreMapperDto
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.data.mappers.MovieAndGenreMapper
import ru.androidschool.intensiv.data.mappers.MovieSearchResultItemMapper
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideActorMapper() = ActorMapper()

    @Provides
    @Singleton
    fun provideActorMapperDto() = ActorMapperDto()

    @Provides
    @Singleton
    fun provideGenreMapper() = GenreMapper()

    @Provides
    @Singleton
    fun provideGenreMapperDto() = GenreMapperDto()

    @Provides
    @Singleton
    fun provideMovieAndActorMapper() = MovieAndActorMapper()

    @Provides
    @Singleton
    fun provideMovieAndGenreMapper() = MovieAndGenreMapper()

    @Provides
    @Singleton
    fun provideMovieStorageMapper() = MovieStorageMapper()

    @Provides
    @Singleton
    fun provideMovieSearchResultItemMapper() = MovieSearchResultItemMapper()

    @Provides
    @Singleton
    fun provideMovieTvShowsStorageMapper() = TvShowsStorageMapper()
}