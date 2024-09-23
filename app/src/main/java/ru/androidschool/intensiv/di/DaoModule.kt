package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.core.storage.database.MoviesDataBase


@Module
class DaoModule {
    @ApplicationScope
    @Provides
    fun provideActorDao(database: MoviesDataBase) = database.getActorDao()

    @ApplicationScope
    @Provides
    fun provideGenreDao(database: MoviesDataBase) = database.getGenreDao()

    @ApplicationScope
    @Provides
    fun provideMovieActorDao(database: MoviesDataBase) = database.getMovieActorDao()

    @ApplicationScope
    @Provides
    fun provideMovieDao(database: MoviesDataBase) = database.getMovieDao()

    @ApplicationScope
    @Provides
    fun provideMovieGenreDao(database: MoviesDataBase) = database.getMovieGenreDao()

    @ApplicationScope
    @Provides
    fun provideTvShowsDao(database: MoviesDataBase) = database.getTvShowsDao()
}