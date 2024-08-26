package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.MovieGenreEntity

@Dao
interface MovieGenreDao {
    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME}")
    fun getAll(): Single<List<MovieGenreEntity>>

    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME} WHERE ${MovieGenreEntity.MOVIE_ID} = :moveId")
    fun getByNoteId(moveId: String): Single<List<MovieGenreEntity>>

    @Query("SELECT * FROM ${MovieGenreEntity.TABLE_NAME} WHERE ${MovieGenreEntity.GENRE_ID} = :genreId")
    fun getByTagId(genreId: Long): Single<List<MovieGenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(moveAndActor: MovieGenreEntity): Completable

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
}