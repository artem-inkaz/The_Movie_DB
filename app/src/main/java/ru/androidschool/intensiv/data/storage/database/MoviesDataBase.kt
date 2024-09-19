package ru.androidschool.intensiv.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.data.storage.converters.GenreConverters
import ru.androidschool.intensiv.data.storage.dao.ActorDao
import ru.androidschool.intensiv.data.storage.dao.GenreDao
import ru.androidschool.intensiv.data.storage.dao.MovieActorDao
import ru.androidschool.intensiv.data.storage.dao.MovieDao
import ru.androidschool.intensiv.data.storage.dao.MovieGenreDao
import ru.androidschool.intensiv.data.storage.dao.TvShowsDao
import ru.androidschool.intensiv.data.storage.entities.ActorEntity
import ru.androidschool.intensiv.data.storage.entities.GenreEntity
import ru.androidschool.intensiv.data.storage.entities.MovieActorEntity
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.data.storage.entities.MovieGenreEntity
import ru.androidschool.intensiv.data.storage.entities.TvShowsEntity
import ru.androidschool.utils.Constants.DATABASE_NAME

@Database(
    entities = [MovieEntity::class, ActorEntity::class, GenreEntity::class,
        MovieActorEntity::class, MovieGenreEntity::class, TvShowsEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(GenreConverters::class)
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
                    MoviesDataBase::class.java, DATABASE_NAME
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