package ru.androidschool.intensiv.extensions

import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.presentation.feed.MainCardContainer
import ru.androidschool.intensiv.presentation.feed.MovieItem
import ru.androidschool.utils.Constants.VOTEAVERAGE
import java.util.Calendar
import java.util.Locale

// Current date
val calendar = Calendar.getInstance()
val currentYear = calendar.get(Calendar.YEAR)

fun getLanguage() = Locale.getDefault().toLanguageTag()

fun voteAverage(voteAverage: Double): Float {
    return voteAverage.div(VOTEAVERAGE).toFloat()
}

fun getMoviesGroupList(
    title: String,
    results: List<MovieLocal>?,
    openMovieDetails: (MovieLocal) -> Unit
): MainCardContainer? {
    val moviesList =
        results?.map { movieLocal ->
            MovieItem(
                content = movieLocal,
                onClick = { movie ->
                    openMovieDetails(
                        movie
                    )
                })
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