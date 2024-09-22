package ru.androidschool.intensiv.core.storage.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.core.storage.database.MoviesDataBase

@Module
object StorageModule {

    @StorageScope
    @Provides
    fun provideDb(app: Context): MoviesDataBase {
        return MoviesDataBase.get(app)
    }
}