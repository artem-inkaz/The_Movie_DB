package ru.androidschool.intensiv.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.androidschool.intensiv.core.storage.entities.TvShowsEntity

@Dao
interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(movie: TvShowsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movie: List<TvShowsEntity>)

    @Update
    suspend fun update(movie: TvShowsEntity)

    @Delete
    suspend fun delete(movie: TvShowsEntity)

    @Transaction
    @Query("DELETE FROM ${TvShowsEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${TvShowsEntity.TABLE_NAME} ORDER BY ${TvShowsEntity.TV_SHOWS_ID} DESC")
    fun getAllTvShows(): Flow<List<TvShowsEntity>>

    @Query(
        """
        SELECT *
        FROM ${TvShowsEntity.TABLE_NAME}
        WHERE ${TvShowsEntity.TV_SHOWS_ID}=:id
        LIMIT 1
        """
    )
    fun getById(id: Int): Flow<TvShowsEntity>

    @Transaction
    @Query(
        """
            SELECT * 
            FROM ${TvShowsEntity.TABLE_NAME} 
            WHERE ${TvShowsEntity.NAME} 
            LIKE '%' || :searchQuery || '%' 
            ORDER BY ${TvShowsEntity.NAME} DESC
            """
    )
    suspend fun search(searchQuery: String): List<TvShowsEntity>
}