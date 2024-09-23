package ru.androidschool.intensiv.data.datasource.api.search

import io.reactivex.Observable
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResponse

interface SearchMovieDataSource {
    fun search(query: String): Observable<MovieSearchResponse>
}