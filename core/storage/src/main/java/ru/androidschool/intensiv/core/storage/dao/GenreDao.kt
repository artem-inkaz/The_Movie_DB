package ru.androidschool.intensiv.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.core.storage.entities.GenreEntity

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