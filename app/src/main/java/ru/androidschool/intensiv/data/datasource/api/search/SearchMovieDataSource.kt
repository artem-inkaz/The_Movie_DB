package ru.androidschool.intensiv.data.datasource.api.search

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.movies.MoviesResponse
import ru.androidschool.intensiv.data.dto.search.MovieSearchResponse

interface SearchMovieDataSource {
    fun search(query: String): Observable<MovieSearchResponse>
}