package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM ${GenreEntity.TABLE_NAME}")
    fun getAll(): Observable<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(link: GenreEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(link: List<GenreEntity>): Completable

    @Update
    fun update(link: GenreEntity): Completable

    @Delete
    fun delete(link: GenreEntity): Completable
}