package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.data.storage.entities.MoviesEntity

@Dao
interface MoviesDao {
    @Transaction
    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} ORDER BY ${MovieEntity.MOVIE_ID} DESC")
    fun getAll(): Single<List<MoviesEntity>>

    @Transaction
    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.MOVIE_ID} = :moveId")
    fun getMovieById(moveId: Int): Single<MoviesEntity>

    @Transaction
    @Query(
        "SELECT * " +
                "FROM ${MovieEntity.TABLE_NAME} " +
                "WHERE ${MovieEntity.TITLE} " +
                "LIKE '%' || :searchQuery || '%' " +
                "OR ${MovieEntity.OVERVIEW} " +
                "LIKE '%' || :searchQuery || '%' " +
                "ORDER BY ${MovieEntity.TITLE} DESC "
    )
    fun searchNotes(searchQuery: String): Single<List<MoviesEntity>>

    @Transaction
    @Query(
        """ 
            SELECT * 
            FROM ${MovieEntity.TABLE_NAME} 
            WHERE ${MovieEntity.LIKE} = 1  
            ORDER BY ${MovieEntity.TITLE} ASC
            """
    )
    fun getAllFavourites(): Single<List<MoviesEntity>>
}