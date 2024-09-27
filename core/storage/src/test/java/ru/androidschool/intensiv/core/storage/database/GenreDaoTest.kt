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
    var instantGenreExecutorRule = InstantTaskExecutorRule()
    // delete Genre
//    @get:Rule
//    var rxSchedulerRule = RxSchedulerRule()

    @Test
    fun insertGenreTest() {
        // 3
        val testGenre = GenreEntity(555, "Test Genre")
        // 4
        databaseRule.appDatabase.getGenreDao().add(testGenre).test()
        // 5
        val genreSize =
            databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values()
                .first().size
        // 6
        assertEquals(genreSize, 1)
    }

    @Test
    fun updateGenreTest() {
        val testGenre = GenreEntity(555, "Test Genre 1111")
        databaseRule.appDatabase.getGenreDao().add(testGenre).test()
        val newTitle = "Test Genre 1112"
        val updatedGenre = testGenre.copy(name = newTitle)

        databaseRule.appDatabase.getGenreDao().update(updatedGenre).test()

        val actualTitle = databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values().first(){ it.first().name == newTitle }.first().name
        assertEquals(newTitle, actualTitle)
    }
//
    @Test
    fun deleteGenreTest() {
        val testGenre = GenreEntity(555, "Test Genre 1111")
        databaseRule.appDatabase.getGenreDao().add(testGenre).test()
        val genreSize = databaseRule.appDatabase.getGenreDao().getAll().test().values().size
//        assertEquals(genreSize, 1)

        databaseRule.appDatabase.getGenreDao().delete(testGenre).test()
        val newGenreSize = databaseRule.appDatabase.getGenreDao().getAll().test().assertNoErrors().values().size
        assertEquals(newGenreSize, 0)
    }

    @Test
    fun getGenreTest() {
        val testGenre = GenreEntity(5, "Test Genre")
        databaseRule.appDatabase.getGenreDao().add(testGenre).test()
        val genreSize = databaseRule.appDatabase.getGenreDao().getAll().test().values().first()?.size
        assertEquals(genreSize, 1)
    }
}