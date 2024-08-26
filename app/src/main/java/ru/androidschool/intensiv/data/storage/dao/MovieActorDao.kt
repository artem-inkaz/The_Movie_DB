package ru.androidschool.intensiv.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.storage.entities.MovieActorEntity

@Dao
interface MovieActorDao {
    @Query("SELECT * FROM ${MovieActorEntity.TABLE_NAME}")
    fun getAll(): Single<List<MovieActorEntity>>

    @Query("SELECT * FROM ${MovieActorEntity.TABLE_NAME} WHERE ${MovieActorEntity.MOVIE_ID} = :moveId")
    fun getByMovieId(moveId: String): Single<List<MovieActorEntity>>

    @Query("SELECT * FROM ${MovieActorEntity.TABLE_NAME} WHERE ${MovieActorEntity.ACTOR_ID} = :actorId")
    fun getByActorId(actorId: Long): Single<List<MovieActorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(moveAndActor: MovieActorEntity): Completable

    @Delete
    fun delete(moveAndActor: MovieActorEntity): Completable

    @Query(
        """ 
            DELETE 
            FROM ${MovieActorEntity.TABLE_NAME} 
            WHERE ${MovieActorEntity.MOVIE_ID} = :moveId 
            AND ${MovieActorEntity.ACTOR_ID} = :actorId
            """
    )
    fun delete(moveId: String, actorId: Long): Completable
}