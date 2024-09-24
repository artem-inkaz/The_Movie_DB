package ru.androidschool.intensiv.core.storage.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.androidschool.intensiv.core.storage.entities.GenreEntity

@RunWith(AndroidJUnit4::class)
class GenreDaoTest {

    // 1
    @get:Rule
    val databaseRule = DatabaseRule()

    // 2
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    // delete Task
//    @get:Rule
//    var rxSchedulerRule = RxSchedulerRule()

    @Test
    fun insertTaskTest() {
        // 3
        val testTask = GenreEntity(555, "Test Genre")
        // 4
        databaseRule.appDatabase.getGenreDao().add(testTask).test()
        // 5
        val taskSize =
            databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values()
                .first().size
        // 6
        assertEquals(taskSize, 1)
    }

    @Test
    fun updateTaskTest() {
        val testTask = GenreEntity(555, "Test Genre 1111")
        databaseRule.appDatabase.getGenreDao().add(testTask).test()
        val newTitle = "Test Genre 1112"
        val updatedTask = testTask.copy(name = newTitle)

        databaseRule.appDatabase.getGenreDao().update(updatedTask).test()

        val actualTitle = databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values().first(){ it.first().name == newTitle }.first().name
        assertEquals(newTitle, actualTitle)
    }
//
    @Test
    fun deleteTaskTest() {
        val testTask = GenreEntity(555, "Test Genre 1111")
        databaseRule.appDatabase.getGenreDao().add(testTask).test()
        val taskSize = databaseRule.appDatabase.getGenreDao().getAll().test().values().size
//        assertEquals(taskSize, 1)

        databaseRule.appDatabase.getGenreDao().delete(testTask).test()
        val newTaskSize = databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values().size
        assertEquals(newTaskSize, 0)
    }

    @Test
    fun getTaskTest() {
        val testTask = GenreEntity(5, "Test Genre")
        databaseRule.appDatabase.getGenreDao().add(testTask).test()
        val taskSize = databaseRule.appDatabase.getGenreDao().getAll().test().values().first()?.size
        assertEquals(taskSize, 1)
    }
}