package ru.androidschool.intensiv.data.datasource.api.search

import io.reactivex.Observable
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResponse

import javax.inject.Inject

class SearchMovieNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    SearchMovieDataSource {
    override fun search(query: String): Observable<MovieSearchResponse> {
        return apiClient.findMovies(query)
    }
}