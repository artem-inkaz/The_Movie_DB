package ru.androidschool.intensiv.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.data.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.moveid.MovieId
import ru.androidschool.intensiv.data.moveidcredits.MoveIdCreditsResponse
import ru.androidschool.intensiv.data.movies.MoviesResponse
import ru.androidschool.intensiv.data.tvseries.TvShowsResponse

interface MovieApiInterface {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Call<MoviesResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Call<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: MovieId,
        @Query("language") language: String = "en-US",
    ): Call<MoveIdResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieIdCredits(
        @Path("movie_id") id: MovieId,
        @Query("language") language: String = "en-US",
    ): Call<MoveIdCreditsResponse>
}
