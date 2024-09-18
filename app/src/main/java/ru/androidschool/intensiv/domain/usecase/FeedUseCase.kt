package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.dto.movies.Movie
import ru.androidschool.intensiv.presentation.feed.GroupFilms
import javax.inject.Inject
import javax.inject.Named

class FeedUseCase @Inject constructor(
   /* @Named("NowPlaying") */private val repositoryNowPlayingMovie: MovieNowPlayingNetworkDataSource,
   /* @Named("Popular")*/ private val repositoryPopularMovie: MoviePopularNetworkDataSource,
   /* @Named("UpComming")*/ private val repositoryUpCommingMovie: MovieUpCommingNetworkDataSource
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
        )
    }
}