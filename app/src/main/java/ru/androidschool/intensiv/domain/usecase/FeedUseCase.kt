package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.core.network.dto.movies.Movie
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.feed.GroupFilms
import javax.inject.Inject

class FeedUseCase @Inject constructor(
   private val repositoryNowPlayingMovie: MovieNowPlayingNetworkDataSource,
   private val repositoryPopularMovie: MoviePopularNetworkDataSource,
   private val repositoryUpCommingMovie: MovieUpCommingNetworkDataSource
) {
    operator fun invoke(): Single<HashMap<GroupFilms, List<Movie>>> {
        return Single.zip(
            repositoryNowPlayingMovie.getMovies(),
            repositoryUpCommingMovie.getMovies(),
            repositoryPopularMovie.getMovies(),
            Function3 { nowPlayingMovies, upComingMovies, popularMovies ->
                hashMapOf(
                    GroupFilms.NOW_PLAYING to nowPlayingMovies.results,
                    GroupFilms.UPCOMING to upComingMovies.results,
                    GroupFilms.POPULAR to popularMovies.results
                )
            }
        ).applySchedulers()
    }
}