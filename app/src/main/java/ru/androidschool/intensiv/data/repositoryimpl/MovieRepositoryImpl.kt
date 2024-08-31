package ru.androidschool.intensiv.data.repositoryimpl

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.domain.repository.MovieRepository

class MovieRepositoryImpl : MovieRepository {

    private val moviesDB = MoviesDataBase.instance//MovieFinderApp.database
    private val movieMapper = MovieStorageMapper()

    override fun create(movie: MovieLocal) {
        return moviesDB.getMovieDao().create(movieMapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<MovieLocal>) {
        return moviesDB.getMovieDao().insertAll( movieMapper.toLocalDataBase(movies))
    }

    override fun update(movie: MovieLocal) {
        return moviesDB.getMovieDao().update(movieMapper.toLocalDataBase(movie))
    }

    override fun delete(movie: MovieLocal) {
        return moviesDB.getMovieDao().delete(movieMapper.toLocalDataBase(movie))
    }

    override fun deleteAll() {
        return moviesDB.getMovieDao().deleteAll()
    }

    override fun getAllMovies(): Single<List<MovieLocal>> {
        return moviesDB.getMovieDao().getAllMovies()
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getById(id: Int): Single<MovieLocal> {
        return moviesDB.getMovieDao().getById(id).map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getByMovieGroup(group: String): Single<List<MovieLocal>> {
        return moviesDB.getMovieDao().getByMovieGroup(group)
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun getFavouriteMovies(): Single<List<MovieLocal>> {
        return moviesDB.getMovieDao().getFavouriteMovies()
            .map { movieMapper.fromLocalDataBase(it) }
    }

    override fun search(searchQuery: String): Single<List<MovieLocal>> {
        return moviesDB.getMovieDao().search(searchQuery)
            .map { movieMapper.fromLocalDataBase(it) }
    }
}