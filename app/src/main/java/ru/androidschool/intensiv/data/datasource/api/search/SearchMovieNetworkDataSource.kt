package ru.androidschool.intensiv.data.datasource.api.search

import io.reactivex.Observable
import ru.androidschool.intensiv.data.dto.search.MovieSearchResponse
import ru.androidschool.intensiv.data.network.MovieApiInterface
import javax.inject.Inject

class SearchMovieNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    SearchMovieDataSource {
    override fun search(query: String): Observable<MovieSearchResponse> {
        return apiClient.findMovies(query)
    }
}