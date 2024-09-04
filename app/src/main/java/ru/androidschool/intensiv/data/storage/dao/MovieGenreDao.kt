package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.data.storage.entities.GenreEntity
import ru.androidschool.intensiv.data.storage.entities.GenreWithMovie
import ru.androidschool.intensiv.data.storage.entities.MovieGenreEntity

@Dao
interface MovieGenreDao {
    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME}")
    fun getAll(): Single<List<MovieGenreEntity>>

    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME} WHERE ${MovieGenreEntity.MOVIE_ID} = :moveId")
    fun getByMovieId(moveId: String): Single<List<MovieGenreEntity>>

    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME} WHERE ${MovieGenreEntity.GENRE_ID} = :genreId")
    fun getByGenreId(genreId: Int): Single<List<MovieGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(moveAndActor: MovieGenreEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(moveAndActor: List<MovieGenreEntity>)

    @Delete
    fun delete(moveAndActor: MovieGenreEntity): Completable

    @Query(
        """ 
            DELETE 
            FROM ${MovieGenreEntity.TABLE_NAME} 
            WHERE ${MovieGenreEntity.MOVIE_ID} = :moveId 
            AND ${MovieGenreEntity.GENRE_ID} = :actorId
            """
    )
    fun delete(moveId: String, actorId: Long): Completable

    /**
     *  One to Many
     */
    @Transaction
    @Query("SELECT * FROM ${GenreEntity.TABLE_NAME} ORDER BY ${GenreEntity.GENRE_ID} DESC")
    fun getGenresWithMovies(): Single<List<GenreWithMovie>>

    @Transaction
    @Query("SELECT * FROM ${GenreEntity.TABLE_NAME} WHERE ${GenreEntity.GENRE_ID}=:genreId")
    fun getGenresWithMovieById(genreId: Int): Single<GenreWithMovie?>
}