package ru.androidschool.intensiv.extensions

import android.content.Context
import androidx.annotation.StringRes
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.data.mappers.fromApiToMovieDomain
import ru.androidschool.intensiv.data.dto.movies.Movie
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
    context: Context,
    @StringRes title: Int,
    results: List<Movie>?,
    openMovieDetails: (MovieLocal) -> Unit
): MainCardContainer? {
    val moviesList =
        results?.map {
            MovieItem(
                content = fromApiToMovieDomain(it, context.getString(title)),
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