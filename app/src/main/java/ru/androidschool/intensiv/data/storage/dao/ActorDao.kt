package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.data.storage.entities.ActorEntity

@Dao
interface ActorDao {
    @Query("SELECT * FROM ${ActorEntity.TABLE_NAME}")
    fun getAll(): Observable<List<ActorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(link: ActorEntity): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<ActorEntity>): Completable

    @Update
    fun update(link: ActorEntity): Completable

    @Delete
    fun delete(link: ActorEntity): Completable
}