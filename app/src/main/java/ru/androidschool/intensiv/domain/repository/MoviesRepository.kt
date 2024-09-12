package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movies

interface MoviesRepository {
    fun getAll(): Single<List<Movies>>
    fun getMovieById(moveId: Int): Single<Movies>
    fun searchNotes(searchQuery: String): Single<List<Movies>>
    fun getAllFavourites(): Single<List<Movies>>
}