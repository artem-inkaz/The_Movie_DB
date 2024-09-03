package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {

    private val moviesDB = MoviesDataBase.instance.getMovieDao()
    private val movieMapper = MovieStorageMapper()

    override fun create(movie: MovieLocal) {
        return moviesDB.create(movieMapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<MovieLocal>) {
        return moviesDB.insertAll(movieMapper.toLocalDataBase(movies))
    }

    override fun update(movie: MovieLocal) {
        return moviesDB.update(movieMapper.toLocalDataBase(movie))
    }

    override fun delete(movie: MovieLocal) {
        return moviesDB.delete(movieMapper.toLocalDataBase(movie))
    }

    override fun deleteAll() {
        return moviesDB.deleteAll()
    }

    override fun getAllMovies(): Single<List<MovieLocal>> {
        return moviesDB.getAllMovies()
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Single<MovieLocal> {
        return moviesDB.getById(id).map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getByMovieGroup(group: String): Single<List<MovieLocal>> {
        return moviesDB.getByMovieGroup(group)
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getFavouriteMovies(): Single<List<MovieLocal>> {
        return moviesDB.getFavouriteMovies()
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getFavouriteMoviesById(id: Int): Single<Boolean> {
        return moviesDB.existFavouriteMovie(id)
    }

    override fun search(searchQuery: String): Single<List<MovieLocal>> {
        return moviesDB.search(searchQuery)
            .map { movieMapper.fromLocalDataBase(it) }
    }
}