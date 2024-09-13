package ru.androidschool.intensiv.presentation.feed.viewmodel

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function3
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.androidschool.intensiv.core.base.BaseViewModel
import ru.androidschool.intensiv.data.datasource.api.movie.MovieNowPlayingNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MoviePopularNetworkDataSource
import ru.androidschool.intensiv.data.datasource.api.movie.MovieUpCommingNetworkDataSource
import ru.androidschool.intensiv.data.mappers.fromApiToMovieDomain
import ru.androidschool.intensiv.data.mappers.fromApiToMovieGenreDomain
import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.usecase.MovieGenreUseCase
import ru.androidschool.intensiv.domain.usecase.MovieStorageUseCase
import ru.androidschool.intensiv.extensions.applySchedulers
import timber.log.Timber

class FeedViewModel(
    private val repositoryNowPlayingMovie: MovieNowPlayingNetworkDataSource,
    private val repositoryPopularMovie: MoviePopularNetworkDataSource,
    private val repositoryUpCommingMovie: MovieUpCommingNetworkDataSource,
    private val useCaseMovieGenre: MovieGenreUseCase,
    private val useCaseMovieFromStorage: MovieStorageUseCase
) : BaseViewModel() {

    private val _movieState = MutableStateFlow(MovieState.initialState)
    val movieState: StateFlow<MovieState> = _movieState.asStateFlow()
    private val moviesLocalGroupList = mutableListOf<List<MovieLocal>>()
    private val movieGenreList = mutableListOf<List<MovieGenre>>()

    fun getMovies() {
        compositeDisposable.add(
            Single.zip(
                repositoryNowPlayingMovie.getMovies(),
                repositoryUpCommingMovie.getMovies(),
                repositoryPopularMovie.getMovies(),
                Function3 { nowPlayingMovies, upComingMovies, popularMovies ->

                    val nowPlaying = "Сейчас в кино"
                    val upcoming = "Новинки"
                    val popular = "Популярные"
                    val nowPlayingMoviesLocalList = nowPlayingMovies.results.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            nowPlaying
                        )
                    }
                    moviesLocalGroupList.add(nowPlayingMoviesLocalList)
                    val nowPlayingMoviesGenre = nowPlayingMovies.results.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    movieGenreList.addAll(nowPlayingMoviesGenre)
                    val upComingMoviesLocalList = upComingMovies.results.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            upcoming
                        )
                    }
                    moviesLocalGroupList.add(upComingMoviesLocalList)
                    val upComingMoviesGenre = upComingMovies.results.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    movieGenreList.addAll(upComingMoviesGenre)
                    val popularMoviesLocalList = popularMovies.results.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            popular
                        )
                    }
                    moviesLocalGroupList.add(popularMoviesLocalList)
                    val popularMoviesGenre = upComingMovies.results.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    movieGenreList.addAll(popularMoviesGenre)
                })
                .applySchedulers()
                .doOnSubscribe {
                    _movieState.value = _movieState.value.copy(isLoading = true)
                }
                .doFinally {
                    _movieState.value = _movieState.value.copy(isLoading = false)
                    saveToStorage()
                }
                .doOnError {
                    Timber.tag(TAG).d("Error doOnError: %s", it.message)
                }
                .subscribe(
                    {
                        _movieState.value = _movieState.value.copy(
                            isLoading = false,
                            moviesLocalGroupList = moviesLocalGroupList,
                            movieGenreList = movieGenreList
                        )
                    },
                    {
                        _movieState.value = _movieState.value.copy(
                            isLoading = false,
                            error = it
                        )
                        Timber.tag(TAG).d("Error subscribe : %s", it.message)
                    }
                )
        )
    }

    private fun saveToStorage() {
        Completable.fromAction {
            _movieState.value.moviesLocalGroupList.forEach {
                useCaseMovieFromStorage.invoke(it)
            }
            _movieState.value.movieGenreList.forEach { movieGenre ->
                useCaseMovieGenre.invoke(movieGenre)
            }
        }.applySchedulers()
            .subscribe()
    }

    companion object {
        private const val TAG = "FeedViewModel"
    }
}