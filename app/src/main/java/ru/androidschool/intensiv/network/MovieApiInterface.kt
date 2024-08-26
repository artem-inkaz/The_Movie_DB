package ru.androidschool.intensiv.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.data.response.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.response.moveid.MovieId
import ru.androidschool.intensiv.data.response.moveidcredits.MoveIdCreditsResponse
import ru.androidschool.intensiv.data.response.movies.MoviesResponse
import ru.androidschool.intensiv.data.response.search.MovieSearchResponse
import ru.androidschool.intensiv.data.response.tvseries.TvShowsResponse
import ru.androidschool.intensiv.extensions.currentYear
import ru.androidschool.intensiv.extensions.getLanguage
import ru.androidschool.utils.Constants.PAGE
import ru.androidschool.utils.Constants.REGION

interface MovieApiInterface {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = PAGE,
    ): Single<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = PAGE,
    ): Single<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = PAGE,
    ): Single<MoviesResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = PAGE,
    ): Single<TvShowsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: MovieId,
        @Query("language") language: String = getLanguage(),
    ): Single<MoveIdResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieIdCredits(
        @Path("movie_id") id: MovieId,
        @Query("language") language: String = getLanguage(),
    ): Single<MoveIdCreditsResponse>

    /**
     * https://ru.wikipedia.org/wiki/ISO_3166-1 - region
     * https://snipp.ru/handbk/iso-639-1 - language
     */
    @GET("search/movie")
    fun findMovies(
        @Query("query") query: String,
        @Query("language") language: String = getLanguage(),
        @Query("include_adult") adult: Boolean = false,
        @Query("region") region: String = REGION,
        @Query("page") page: Int = PAGE,
        @Query("year") year: String = "$currentYear",
    ): Observable<MovieSearchResponse>
}
