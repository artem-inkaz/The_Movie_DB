package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.core.storage.dao.MovieDao
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import javax.inject.Inject

class MovieStorageRepositoryImpl @Inject constructor(
    private val dao: MovieDao,
    private val mapper: MovieStorageMapper
) : MovieStorageRepository {

    override fun create(movie: MovieLocal) {
        return dao.create(mapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<MovieLocal>) {
        return dao.insertAll(mapper.toLocalDataBase(movies))
    }

    override fun update(movie: MovieLocal) {
        return dao.update(mapper.toLocalDataBase(movie))
    }

    override fun delete(movie: MovieLocal) {
        return dao.delete(mapper.toLocalDataBase(movie))
    }

    override fun deleteAll() {
        return dao.deleteAll()
    }

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