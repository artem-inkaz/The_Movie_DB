package ru.androidschool.intensiv.core.storage.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.androidschool.intensiv.core.storage.database.MoviesDataBase
import javax.inject.Scope

@Scope
@Retention
annotation class StorageScope

@Component(modules = [StorageModule::class])
@StorageScope
interface StorageComponent {
    val context: Context
    val db: MoviesDataBase

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): StorageComponent
    }
}