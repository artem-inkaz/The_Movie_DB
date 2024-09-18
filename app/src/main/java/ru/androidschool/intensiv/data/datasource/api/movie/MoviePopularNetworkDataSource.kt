package ru.androidschool.intensiv.data.datasource.api.movie

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movies.MoviesResponse
import ru.androidschool.intensiv.data.dto.search.MovieSearchResponse
import ru.androidschool.intensiv.data.network.MovieApiInterface
import javax.inject.Inject

class MoviePopularNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    MovieDataSource {
    override fun getMovies(): Single<MoviesResponse> {
        return apiClient.getPopularMovies()
    }

    override fun search(query: String): Observable<MovieSearchResponse> {
        return apiClient.findMovies(query)
    }
}