package ru.androidschool.intensiv.data

object MockRepository {

    fun getMovies(): List<MovieLocal> {

        val moviesList = mutableListOf<MovieLocal>()
        for (x in 0..10) {
            val movie = MovieLocal(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x,
                posterPath = "",
                backdropPath = ""
            )
            moviesList.add(movie)
        }

        return moviesList
    }

    fun getShowDest(): List<ShowDest> {

        val showDestList = mutableListOf<ShowDest>()
        for (x in 0..10) {
            val showDest = ShowDest(
                title = "Spiderman: No Way\n" +
                        "Home $x",
                voteAverage = 10.0 - x
            )
            showDestList.add(showDest)
        }

        return showDestList
    }
}
