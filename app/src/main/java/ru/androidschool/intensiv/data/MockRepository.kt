package ru.androidschool.intensiv.data

object MockRepository {

    fun getMovies(): List<Movie> {

        val moviesList = mutableListOf<Movie>()
        for (x in 0..10) {
            val movie = Movie(
                title = "Spider-Man $x",
                voteAverage = 10.0 - x
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
