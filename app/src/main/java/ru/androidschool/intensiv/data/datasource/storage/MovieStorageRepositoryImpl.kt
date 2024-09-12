package ru.androidschool.intensiv.data.datasource.storage

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MovieStorageMapper
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository

class MovieStorageRepositoryImpl(
    private val moviesDB: MoviesDataBase,
    private val movieMapper: MovieStorageMapper
) : MovieStorageRepository {

    override fun create(movie: MovieLocal) {
        return moviesDB.getMovieDao().create(movieMapper.toLocalDataBase(movie))
    }

    override fun insertAll(movies: List<MovieLocal>) {
        return moviesDB.getMovieDao().insertAll(movieMapper.toLocalDataBase(movies))
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

    override fun getFavouriteMoviesById(id: Int): Single<Boolean> {
        return moviesDB.getMovieDao().existFavouriteMovie(id)
    }

    override fun search(searchQuery: String): Single<List<MovieLocal>> {
        return moviesDB.getMovieDao().search(searchQuery)
            .map { movieMapper.fromLocalDataBase(it) }
    }
}