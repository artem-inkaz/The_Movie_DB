package ru.androidschool.intensiv.data.storage.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.MovieFinderApp
import ru.androidschool.intensiv.data.storage.dao.ActorDao
import ru.androidschool.intensiv.data.storage.dao.GenreDao
import ru.androidschool.intensiv.data.storage.dao.MovieActorDao
import ru.androidschool.intensiv.data.storage.dao.MovieDao
import ru.androidschool.intensiv.data.storage.dao.MovieGenreDao
import ru.androidschool.intensiv.data.storage.entities.ActorEntity
import ru.androidschool.intensiv.data.storage.entities.GenreEntity
import ru.androidschool.intensiv.data.storage.entities.MovieActorEntity
import ru.androidschool.intensiv.data.storage.entities.MovieEntity
import ru.androidschool.intensiv.data.storage.entities.MovieGenreEntity
import ru.androidschool.utils.Constants.DATABASE_NAME

@Database(
    entities = [MovieEntity::class, ActorEntity::class, GenreEntity::class,
        MovieActorEntity::class, MovieGenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
    abstract fun getActorDao(): ActorDao
    abstract fun getGenreDao(): GenreDao
    abstract fun getMovieActorDao(): MovieActorDao
    abstract fun getMovieGenreDao(): MovieGenreDao


    companion object {
        val instance: MoviesDataBase by lazy {
            Room.databaseBuilder(
                MovieFinderApp.context(),
                MoviesDataBase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }
}