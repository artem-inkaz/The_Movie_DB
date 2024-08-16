package ru.androidschool.intensiv.data.mock

import ru.androidschool.intensiv.data.MovieLocal

object MockRepository {

    fun getMovies(): List<MovieLocal> {

        val moviesList = mutableListOf<MovieLocal>()
        for (x in 0..10) {
            val movie = MovieLocal(
                id = x,
                title = "Spider-Man $x",
                overview = "",
                voteAverage = 10.0 - x,
                posterPath = "",
                backdropPath = ""
            )
            moviesList.add(movie)
        }

        return moviesList
    }
}