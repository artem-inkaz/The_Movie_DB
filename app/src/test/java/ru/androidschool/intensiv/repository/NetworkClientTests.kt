package ru.androidschool.intensiv.repository

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import java.net.HttpURLConnection

class NetworkClientTests {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var testApis: MovieApiInterface
    private lateinit var networkClient: MovieNowPlayingNetworkDataSource

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        testApis = RetrofitHelper.testApiInstance(mockWebServer.url("/").toString())
        networkClient = MovieNowPlayingNetworkDataSource(testApis)
    }


    @Test
    fun testEndpoint() {
        val expectedNetworkResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(getJson("json/now_playing.json"))

        mockWebServer.enqueue(expectedNetworkResponse)

        val result = networkClient.getMovies().test().assertNoErrors().values().first().results

        Assert.assertEquals(true, result.isNotEmpty())
    }

    @Test
    fun testEndpointWhenHttpNotFound() {
        val expectedNetworkResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody(getJson("json/now_playing.json"))

        mockWebServer.enqueue(expectedNetworkResponse)

        val result = try {
            networkClient.getMovies().test()
            null
        } catch (exception: RuntimeException) {
            exception
        }

        Assert.assertNotEquals(result?.message, "HTTP 404 Client Error")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    object RetrofitHelper {

        fun testApiInstance(baseUrl: String): MovieApiInterface {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieApiInterface::class.java)
        }
    }
}