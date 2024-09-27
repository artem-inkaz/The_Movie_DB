package ru.androidschool.intensiv.core.storage.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabaseRule : TestWatcher() {

    lateinit var appDatabase: MoviesDataBase

    override fun starting(description: Description?) {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDataBase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override fun finished(description: Description?) {
        appDatabase.close()
    }
}