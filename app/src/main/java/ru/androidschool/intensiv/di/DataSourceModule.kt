package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.data.datasource.api.actor.ActorsNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.search.SearchMovieNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.tvshows.TvShowsNetworkDataSource
import javax.inject.Singleton

@Module
class DataSourceModule {
    @ApplicationScope
    @Provides
    fun provideActorsDataSource(apiClient: MovieApiInterface): ActorsNetworkDataSource {
        return ActorsNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun provideMovieDetailDataSource(apiClient: MovieApiInterface): MovieDetailNetworkDataSource {
        return MovieDetailNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun provideNowPlayingMovieDataSource(apiClient: MovieApiInterface): MovieNowPlayingNetworkDataSource {
        return MovieNowPlayingNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun providePopularMovieDataSource(apiClient: MovieApiInterface): MoviePopularNetworkDataSource {
        return MoviePopularNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun provideUpCommingMovieDataSource(apiClient: MovieApiInterface): MovieUpCommingNetworkDataSource {
        return MovieUpCommingNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun provideSearchMovieDataSource(apiClient: MovieApiInterface): SearchMovieNetworkDataSource {
        return SearchMovieNetworkDataSource(apiClient)
    }

    @ApplicationScope
    @Provides
    fun provideTvShowsDataSource(apiClient: MovieApiInterface): TvShowsNetworkDataSource {
        return TvShowsNetworkDataSource(apiClient)
    }
}