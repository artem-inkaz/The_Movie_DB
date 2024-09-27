package ru.androidschool.intensiv.repository

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource

class MovieDataSourceTest {

    @get:Rule
    var testRule = BaseRepositoryTestRule()

    private lateinit var movieDataSource: MovieNowPlayingNetworkDataSource

    @Test
    fun `check movie response success`() {
        movieDataSource = MovieNowPlayingNetworkDataSource(testRule.movieApi)
        val results = movieDataSource.getMovies().test().assertNoErrors()
        Assert.assertEquals(true, results != null)
    }
}