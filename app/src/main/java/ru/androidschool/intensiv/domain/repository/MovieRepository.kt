package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.domain.MovieLocal

interface MovieRepository {
    fun create(movie: MovieLocal)
    fun insertAll(movie: List<MovieLocal>)
    fun update(movie: MovieLocal)
    fun delete(movie: MovieLocal)
    fun deleteAll()
    fun getAllMovies(): Single<List<MovieLocal>>
    fun getById(id: Int): Single<MovieLocal>
    fun getByMovieGroup(group: String): Single<List<MovieLocal>>
    fun getFavouriteMovies(): Single<List<MovieLocal>>
    fun getFavouriteMoviesById(id: Int): Single<Boolean>
    fun search(searchQuery: String): Single<List<MovieLocal>>
}