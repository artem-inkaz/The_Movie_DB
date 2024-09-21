package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsDataSource
import ru.androidschool.intensiv.data.datasource.storage.ActorStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.GenreStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.MovieStorageRepositoryImpl
import ru.androidschool.intensiv.data.datasource.storage.TvShowsStorageRepositoryImpl
import ru.androidschool.intensiv.data.mappers.ActorMapper
import ru.androidschool.intensiv.data.mappers.GenreMapper
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.data.mappers.MovieAndGenreMapper
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.mappers.TvShowsStorageMapper
import ru.androidschool.intensiv.data.repositoryimpl.MovieActorRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.MovieGenreRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.MovieRepositoryImpl
import ru.androidschool.intensiv.data.repositoryimpl.TvShowsRepositoryImpl
import ru.androidschool.intensiv.data.storage.dao.ActorDao
import ru.androidschool.intensiv.data.storage.dao.GenreDao
import ru.androidschool.intensiv.data.storage.dao.MovieActorDao
import ru.androidschool.intensiv.data.storage.dao.MovieDao
import ru.androidschool.intensiv.data.storage.dao.MovieGenreDao
import ru.androidschool.intensiv.data.storage.dao.TvShowsDao
import ru.androidschool.intensiv.domain.datasource.TvShowsStorageRepository
import javax.inject.Singleton

@Module
class StorageRepositoryModule {

    @ApplicationScope
    @Provides
    fun provideActorStorageRepository(
        dao: ActorDao,
        mapper: ActorMapper
    ): ActorStorageRepositoryImpl {
        return ActorStorageRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideGenreStorageRepository(
        dao: GenreDao,
        mapper: GenreMapper
    ): GenreStorageRepositoryImpl {
        return GenreStorageRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideMovieStorageRepository(
        dao: MovieDao,
        mapper: MovieStorageMapper
    ): MovieStorageRepositoryImpl {
        return MovieStorageRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideTvShowsStorageRepositoryImpl(
        dao: TvShowsDao,
        mapper: TvShowsStorageMapper
    ): TvShowsStorageRepositoryImpl {
        return TvShowsStorageRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideMovieActorRepositoryImpl(
        dao: MovieActorDao,
        mapper: MovieAndActorMapper
    ): MovieActorRepositoryImpl {
        return MovieActorRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideMovieGenreRepositoryImpl(
        dao: MovieGenreDao,
        mapper: MovieAndGenreMapper
    ): MovieGenreRepositoryImpl {
        return MovieGenreRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideMovieRepositoryImpl(
        dao: MovieDao,
        mapper: MovieStorageMapper
    ): MovieRepositoryImpl {
        return MovieRepositoryImpl(dao, mapper)
    }

    @ApplicationScope
    @Provides
    fun provideTvShowsRepositoryImpl(
        api: TvShowsDataSource,
        storage: TvShowsStorageRepository
    ): TvShowsRepositoryImpl {
        return TvShowsRepositoryImpl(api, storage)
    }
}