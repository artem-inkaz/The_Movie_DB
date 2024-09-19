package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.mappers.GenreMapperDto
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.data.mappers.MovieAndGenreMapper
import ru.androidschool.intensiv.data.mappers.MovieSearchResultItemMapper
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper

@Module
class MapperModule {
    @Provides
    @Reusable
    fun provideActorMapper() = ActorMapper()

    @Provides
    @Reusable
    fun provideActorMapperDto() = ActorMapperDto()

    @Provides
    @Reusable
    fun provideGenreMapper() = GenreMapper()

    @Provides
    @Reusable
    fun provideGenreMapperDto() = GenreMapperDto()

    @Provides
    @Reusable
    fun provideMovieAndActorMapper() = MovieAndActorMapper()

    @Provides
    @Reusable
    fun provideMovieAndGenreMapper() = MovieAndGenreMapper()

    @Provides
    @Reusable
    fun provideMovieStorageMapper() = MovieStorageMapper()

    @Provides
    @Reusable
    fun provideMovieSearchResultItemMapper() = MovieSearchResultItemMapper()

    @Provides
    @Reusable
    fun provideMovieTvShowsStorageMapper() = TvShowsStorageMapper()
}