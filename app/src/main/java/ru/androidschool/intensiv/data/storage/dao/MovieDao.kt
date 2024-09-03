package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.data.response.moveid.MovieId

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(movie: MovieEntity)

//    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<MovieEntity>)

    @Update
    fun update(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

    @Transaction
    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} ORDER BY ${MovieEntity.MOVIE_ID} DESC")
    fun getAllMovies(): Single<List<MovieEntity>>

    @Query(
        """
        SELECT *
        FROM ${MovieEntity.TABLE_NAME}
        WHERE ${MovieEntity.MOVIE_ID}=:id
        LIMIT 1
        """
    )
    fun getById(id: Int): Single<MovieEntity>

    @Query(
        """
        SELECT *
        FROM ${MovieEntity.TABLE_NAME}
        WHERE ${MovieEntity.MOVIE_GROUP}=:group
        """
    )
    fun getByMovieGroup(group: String): Single<List<MovieEntity>>

    @Query(
        """ 
            SELECT * 
            FROM ${MovieEntity.TABLE_NAME} 
            WHERE ${MovieEntity.LIKE} = 1 
            ORDER BY ${MovieEntity.MOVIE_ID} DESC 
            """
    )
    fun getFavouriteMovies(): Single<List<MovieEntity>>

    @Transaction
    @Query("""
            SELECT * 
            FROM ${MovieEntity.TABLE_NAME} 
            WHERE ${MovieEntity.TITLE} 
            LIKE '%' || :searchQuery || '%' 
            OR ${MovieEntity.OVERVIEW} 
            LIKE '%' || :searchQuery || '%' 
            ORDER BY ${MovieEntity.TITLE} DESC
            """)
    fun search(searchQuery: String): Single<List<MovieEntity>>

    @Transaction
    @Query("SELECT EXISTS (SELECT 1 FROM ${MovieEntity.TABLE_NAME} WHERE ${MovieEntity.MOVIE_ID} =:movieId AND ${MovieEntity.LIKE} = 1)")
    fun existFavouriteMovie(movieId: Int): Single<Boolean>
}