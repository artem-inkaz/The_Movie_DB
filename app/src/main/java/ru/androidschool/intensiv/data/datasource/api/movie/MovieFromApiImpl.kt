package ru.androidschool.intensiv.data.datasource.api.movie

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.movies.MoviesResponse
import ru.androidschool.intensiv.data.dto.search.MovieSearchResponse
import ru.androidschool.intensiv.data.network.MovieApiClient

class MovieFromApiImpl: MovieFromApi {
    override fun getNowPlayingMovies(): Single<MoviesResponse> {
        return MovieApiClient.apiClient.getNowPlayingMovies()
    }

    override fun getUpComingMovies(): Single<MoviesResponse> {
        return MovieApiClient.apiClient.getUpComingMovies()
    }

    override fun getPopularMovies(): Single<MoviesResponse> {
        return MovieApiClient.apiClient.getPopularMovies()
    }

    override fun getMovieDetails(id: MovieId): Single<MoveIdResponse> {
        return MovieApiClient.apiClient.getMovieDetails(id)
    }

    override fun search(query: String): Observable<MovieSearchResponse> {
        return MovieApiClient.apiClient.findMovies(query)
    }
}