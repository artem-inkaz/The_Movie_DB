package ru.androidschool.intensiv.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase

@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun provideDb(app: Context): MoviesDataBase {
        return MoviesDataBase.get(app)
    }
}