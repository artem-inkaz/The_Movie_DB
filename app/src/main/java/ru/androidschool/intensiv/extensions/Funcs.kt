package ru.androidschool.intensiv.extensions

import androidx.annotation.StringRes
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.data.mappers.MovieMapper
import ru.androidschool.intensiv.data.movies.Movie
import ru.androidschool.intensiv.ui.feed.MainCardContainer
import ru.androidschool.intensiv.ui.feed.MovieItem

fun voteAverage(voteAverage: Double): Float {
    return voteAverage.div(2).toFloat()
}

fun getMoviesGroupList(@StringRes title: Int, results: List<Movie>?, openMovieDetails: (MovieLocal) -> Unit): MainCardContainer? {
    val moviesList =
        results?.map {
            MovieItem(MovieMapper().toViewObject(it)) { movie ->
                openMovieDetails(
                    movie
                )
            }
        }?.toList()

    val moviesGroupList =
        moviesList?.let {
            MainCardContainer(
                title = title,
                items = it
            )
        }
    return moviesGroupList
}