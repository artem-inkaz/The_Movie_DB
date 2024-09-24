package ru.androidschool.intensiv.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import ru.androidschool.intensiv.core.storage.database.MoviesDataBase

class RoomTestRule : TestRule {

    val db: MoviesDataBase by lazy { initialiseRoomDb() }

    override fun apply(base: Statement?, description: Description?): Statement {
        return RoomStatement(statement = base)
    }

    inner class RoomStatement(private val statement: Statement?) : Statement() {

        override fun evaluate() {
            try {
                statement?.evaluate()
            } finally {
                db.close()
            }
        }

    }


    private fun initialiseRoomDb(): MoviesDataBase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        MoviesDataBase::class.java
    ).allowMainThreadQueries().build()

}
