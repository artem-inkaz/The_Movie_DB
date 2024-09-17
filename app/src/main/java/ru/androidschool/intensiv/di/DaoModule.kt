package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import javax.inject.Singleton

@Module
class DaoModule {
    @Singleton
    @Provides
    fun provideActorDao(database: MoviesDataBase) = database.getActorDao()

    @Singleton
    @Provides
    fun provideGenreDao(database: MoviesDataBase) = database.getGenreDao()

    @Singleton
    @Provides
    fun provideMovieActorDao(database: MoviesDataBase) = database.getMovieActorDao()

    @Singleton
    @Provides
    fun provideMovieDao(database: MoviesDataBase) = database.getMovieDao()

    @Singleton
    @Provides
    fun provideMovieGenreDao(database: MoviesDataBase) = database.getMovieGenreDao()

    @Singleton
    @Provides
    fun provideTvShowsDao(database: MoviesDataBase) = database.getTvShowsDao()
}