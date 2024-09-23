package ru.androidschool.intensiv.core.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.core.network.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.core.network.dto.moveid.MovieId
import ru.androidschool.intensiv.core.network.dto.moveidcredits.MoveIdCreditsResponse
import ru.androidschool.intensiv.core.network.dto.movies.MoviesResponse
import ru.androidschool.intensiv.core.network.dto.search.MovieSearchResponse
import ru.androidschool.intensiv.core.network.dto.tvseries.TvShowsResponse
import ru.androidschool.intensiv.core.network.utils.currentYear
import ru.androidschool.intensiv.core.network.utils.getLanguage
import ru.androidschool.intensiv.core.network.utils.movieParams

interface MovieApiInterface {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = movieParams.page,
    ): Single<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = movieParams.page,
    ): Single<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = movieParams.page,
    ): Single<MoviesResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("language") language: String = getLanguage(),
        @Query("page") page: Int = movieParams.page,
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
        @Query("region") region: String = movieParams.region,
        @Query("page") page: Int = movieParams.page,
        @Query("year") year: String = "$currentYear",
    ): Observable<MovieSearchResponse>
}
