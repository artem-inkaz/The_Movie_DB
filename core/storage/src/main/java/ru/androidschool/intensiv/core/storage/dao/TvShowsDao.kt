package ru.androidschool.intensiv.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.core.storage.entities.TvShowsEntity

@Dao
interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(movie: TvShowsEntity): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<TvShowsEntity>): Completable

    @Update
    fun update(movie: TvShowsEntity): Completable

    @Delete
    fun delete(movie: TvShowsEntity)

    @Transaction
    @Query("DELETE FROM ${TvShowsEntity.TABLE_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${TvShowsEntity.TABLE_NAME} ORDER BY ${TvShowsEntity.TV_SHOWS_ID} DESC")
    fun getAllTvShows(): Observable<List<TvShowsEntity>>

    @Query(
        """
        SELECT *
        FROM ${TvShowsEntity.TABLE_NAME}
        WHERE ${TvShowsEntity.TV_SHOWS_ID}=:id
        LIMIT 1
        """
    )
    fun getById(id: Int): Observable<TvShowsEntity>

    @Transaction
    @Query("""
            SELECT * 
            FROM ${TvShowsEntity.TABLE_NAME} 
            WHERE ${TvShowsEntity.NAME} 
            LIKE '%' || :searchQuery || '%' 
            ORDER BY ${TvShowsEntity.NAME} DESC
            """)
    fun search(searchQuery: String): Single<List<TvShowsEntity>>
}