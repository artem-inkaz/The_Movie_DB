package ru.androidschool.intensiv.data.datasource.api.movie

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.movies.MoviesResponse
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResponse

import javax.inject.Inject

class MovieUpCommingNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    MovieDataSource {
    override fun getMovies(): Single<MoviesResponse> {
        return apiClient.getUpComingMovies()
    }

    override fun search(query: String): Observable<MovieSearchResponse> {
        return apiClient.findMovies(query)
    }
}