package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM ${GenreEntity.TABLE_NAME}")
    fun getAll(): Single<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(link: GenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(link: List<GenreEntity>)

    @Update
    fun update(link: GenreEntity): Completable

    @Delete
    fun delete(link: GenreEntity): Completable
}