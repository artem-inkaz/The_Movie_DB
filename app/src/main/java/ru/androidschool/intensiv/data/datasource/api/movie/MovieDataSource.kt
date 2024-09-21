package ru.androidschool.intensiv.data.datasource.api.movie

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.core.network.dto.movies.MoviesResponse
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResponse

interface MovieDataSource {
    fun getMovies(): Single<MoviesResponse>
    fun search(query: String): Observable<MovieSearchResponse>
}