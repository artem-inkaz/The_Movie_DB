package ru.androidschool.intensiv.core.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.core.storage.BuildConfig
import ru.androidschool.intensiv.core.storage.dao.ActorDao
import ru.androidschool.intensiv.core.storage.dao.GenreDao
import ru.androidschool.intensiv.core.storage.dao.MovieActorDao
import ru.androidschool.intensiv.core.storage.dao.MovieDao
import ru.androidschool.intensiv.core.storage.dao.MovieGenreDao
import ru.androidschool.intensiv.core.storage.dao.TvShowsDao
import ru.androidschool.intensiv.core.storage.entities.ActorEntity
import ru.androidschool.intensiv.core.storage.entities.GenreEntity
import ru.androidschool.intensiv.core.storage.entities.MovieActorEntity
import ru.androidschool.intensiv.core.storage.entities.MovieEntity
import ru.androidschool.intensiv.core.storage.entities.MovieGenreEntity
import ru.androidschool.intensiv.core.storage.entities.TvShowsEntity
import ru.androidschool.intensiv.core.storage.utils.movieParams

@Database(
    entities = [MovieEntity::class, ActorEntity::class, GenreEntity::class,
        MovieActorEntity::class, MovieGenreEntity::class, TvShowsEntity::class],
    version = 4,
    exportSchema = false
)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getActorDao(): ActorDao
    abstract fun getGenreDao(): GenreDao
    abstract fun getMovieActorDao(): MovieActorDao
    abstract fun getMovieGenreDao(): MovieGenreDao
    abstract fun getTvShowsDao(): TvShowsDao

    companion object {
        private var instance: MoviesDataBase? = null

        @Synchronized
        fun get(context: Context): MoviesDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java, movieParams.databaseName
                ).apply {
                    if (BuildConfig.DEBUG) {
                        fallbackToDestructiveMigration()
                    }
                }
                    .build()
            }
            return instance!!
        }
    }
}