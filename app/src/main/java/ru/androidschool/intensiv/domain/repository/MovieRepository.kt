package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.MovieLocal

interface MovieRepository {
    fun getAllMovies(): Single<List<MovieLocal>>
    fun getById(id: Int): Single<MovieLocal>
    fun getByMovieGroup(group: String): Single<List<MovieLocal>>
    fun getFavouriteMovies(): Single<List<MovieLocal>>
    fun getFavouriteMoviesById(id: Int): Single<Boolean>
    fun search(searchQuery: String): Single<List<MovieLocal>>
}