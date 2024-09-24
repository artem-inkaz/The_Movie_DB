package ru.androidschool.intensiv.repository

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource

class MovieDataSourceTest: BaseRepositoryTest() {
    private lateinit var movieDataSource: MovieNowPlayingNetworkDataSource

    @Before
    override fun setup() {
        super.setup()
        movieDataSource = MovieNowPlayingNetworkDataSource(movieApi)
    }

    @Test
    fun `check response success`() {
            val results = movieDataSource.getMovies().test().assertNoErrors()
            Assert.assertEquals(true, results != null)
    }
}