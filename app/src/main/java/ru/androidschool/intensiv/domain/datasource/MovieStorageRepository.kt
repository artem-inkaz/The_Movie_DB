package ru.androidschool.intensiv.domain.datasource

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.MovieLocal

interface MovieStorageRepository {
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