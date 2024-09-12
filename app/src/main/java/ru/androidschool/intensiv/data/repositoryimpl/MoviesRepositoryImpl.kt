package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movies
import ru.androidschool.intensiv.domain.repository.MoviesRepository

class MoviesRepositoryImpl: MoviesRepository {
    override fun getAll(): Single<List<Movies>> {
        TODO("Not yet implemented")
    }

    override fun getMovieById(moveId: Int): Single<Movies> {
        TODO("Not yet implemented")
    }

    override fun searchNotes(searchQuery: String): Single<List<Movies>> {
        TODO("Not yet implemented")
    }

    override fun getAllFavourites(): Single<List<Movies>> {
        TODO("Not yet implemented")
    }
}