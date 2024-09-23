package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.core.storage.dao.MovieDao
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dao: MovieDao,
    private val mapper: MovieStorageMapper
) : MovieRepository {

    override fun getAllMovies(): Single<List<MovieLocal>> {
        return dao.getAllMovies()
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Single<MovieLocal> {
        return dao.getById(id).map { mapper.fromLocalDataBase(it) }
    }

    override fun getByMovieGroup(group: String): Single<List<MovieLocal>> {
        return dao.getByMovieGroup(group)
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getFavouriteMovies(): Single<List<MovieLocal>> {
        return dao.getFavouriteMovies()
            .map { mapper.fromLocalDataBase(it) }
    }

    override fun getFavouriteMoviesById(id: Int): Single<Boolean> {
        return dao.existFavouriteMovie(id)
    }

    override fun search(searchQuery: String): Single<List<MovieLocal>> {
        return dao.search(searchQuery)
            .map { mapper.fromLocalDataBase(it) }
    }
}