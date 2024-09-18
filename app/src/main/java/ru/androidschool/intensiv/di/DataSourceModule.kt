package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.datasource.api.actor.ActorsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.search.SearchMovieNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsNetworkDataSource
import ru.androidschool.intensiv.data.network.MovieApiInterface
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Singleton
    @Provides
    fun provideActorsDataSource(apiClient: MovieApiInterface): ActorsNetworkDataSource {
        return ActorsNetworkDataSource(apiClient)
    }

    @Singleton
    @Provides
    fun provideMovieDetailDataSource(apiClient: MovieApiInterface): MovieDetailNetworkDataSource {
        return MovieDetailNetworkDataSource(apiClient)
    }

    @Singleton
//    @Named("NowPlaying")
    @Provides
    fun provideNowPlayingMovieDataSource(apiClient: MovieApiInterface): MovieNowPlayingNetworkDataSource {
        return MovieNowPlayingNetworkDataSource(apiClient)
    }

    @Singleton
//    @Named("Popular")
    @Provides
    fun providePopularMovieDataSource(apiClient: MovieApiInterface): MoviePopularNetworkDataSource {
        return MoviePopularNetworkDataSource(apiClient)
    }

    @Singleton
//    @Named("UpComming")
    @Provides
    fun provideUpCommingMovieDataSource(apiClient: MovieApiInterface): MovieUpCommingNetworkDataSource {
        return MovieUpCommingNetworkDataSource(apiClient)
    }

    @Singleton
    @Provides
    fun provideSearchMovieDataSource(apiClient: MovieApiInterface): SearchMovieNetworkDataSource {
        return SearchMovieNetworkDataSource(apiClient)
    }

    @Singleton
    @Provides
    fun provideTvShowsDataSource(apiClient: MovieApiInterface): TvShowsNetworkDataSource {
        return TvShowsNetworkDataSource(apiClient)
    }
}