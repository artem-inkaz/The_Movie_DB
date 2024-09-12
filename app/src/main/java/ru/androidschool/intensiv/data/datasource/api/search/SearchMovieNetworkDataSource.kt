package ru.androidschool.intensiv.data.datasource.api.search

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movies.MoviesResponse
import ru.androidschool.intensiv.data.dto.search.MovieSearchResponse
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.network.MovieApiInterface

class SearchMovieNetworkDataSource(private val apiClient: MovieApiInterface) :
    SearchMovieDataSource {
    override fun search(query: String): Observable<MovieSearchResponse> {
        return apiClient.findMovies(query)
    }
}