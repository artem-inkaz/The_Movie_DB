package ru.androidschool.intensiv.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.androidschool.intensiv.core.storage.database.MoviesDataBase
import ru.androidschool.intensiv.data.mappers.MovieAndActorMapper
import ru.androidschool.intensiv.domain.repository.MovieActorRepository
import ru.androidschool.intensiv.data.repositoryimpl.MovieActorRepositoryImpl
import ru.androidschool.intensiv.mock.mockMovieActor

@RunWith(AndroidJUnit4::class)
class MovieActorRepositoryTest {

    private lateinit var repository: MovieActorRepository
    val mapper = MovieAndActorMapper()

    @JvmField
    @Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val roomTestRule: RoomTestRule = RoomTestRule()

    private val db: MoviesDataBase = roomTestRule.db

    @Before
    fun setUp() {
        repository = MovieActorRepositoryImpl(db.getMovieActorDao(), mapper)
    }

    @Test
    fun insertMovieActor() {
        val testMovieActor = mockMovieActor
        testMovieActor.forEach{
            repository.add(it).test().awaitTerminalEvent()
        }

       val movieActorSize = repository.getByMovieId(testMovieActor[0].movieId.toString()).test().assertNoErrors().values().size
        assertEquals(movieActorSize, 1)
    }
}