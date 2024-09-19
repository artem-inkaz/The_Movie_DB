package ru.androidschool.intensiv.presentation.feed.viewmodel

import android.util.Log
import io.reactivex.Completable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.androidschool.intensiv.core.base.BaseViewModel
import ru.androidschool.intensiv.data.dto.movies.Movie
import ru.androidschool.intensiv.data.mappers.fromApiToMovieDomain
import ru.androidschool.intensiv.data.mappers.fromApiToMovieGenreDomain
import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.usecase.FeedUseCase
import ru.androidschool.intensiv.domain.usecase.MovieGenreUseCase
import ru.androidschool.intensiv.domain.usecase.MovieStorageUseCase
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.feed.GroupFilms
import timber.log.Timber
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val useCaseFeed: FeedUseCase,
    private val useCaseMovieGenre: MovieGenreUseCase,
    private val useCaseMovieFromStorage: MovieStorageUseCase
) : BaseViewModel() {

    private val _movieState = MutableStateFlow(MovieState.initialState)
    val movieState: StateFlow<MovieState> = _movieState.asStateFlow()
    private var moviesLocalGroupList = HashMap<GroupFilms, List<MovieLocal>>()
    private val movieGenreList = mutableListOf<List<MovieGenre>>()

    init {
        getMovies()
    }

    fun getMovies() {
        compositeDisposable.add(
            useCaseFeed.invoke()
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
                    { data ->
                        getMoviesLocalList(data)
                        _movieState.value = _movieState.value.copy(
                            isLoading = false,
                            moviesLocalGroupList = moviesLocalGroupList,
                            movieGenreList = movieGenreList
                        )
                    }, {
                        _movieState.value = _movieState.value.copy(
                            isLoading = false,
                            error = it
                        )
                        Timber.tag(TAG).d("Error subscribe : %s", it.message)
                    }
                )
        )

    }

    private fun getMoviesLocalList(data: HashMap<GroupFilms, List<Movie>>) {
        data.forEach { movie ->
            when (movie.key) {
                GroupFilms.NOW_PLAYING -> {
                    val nowPlayingMoviesLocalList = data[GroupFilms.NOW_PLAYING]?.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            GroupFilms.NOW_PLAYING.title
                        )
                    }
                    nowPlayingMoviesLocalList?.let {
                        moviesLocalGroupList[GroupFilms.NOW_PLAYING] = it //.add(it)
                    }
                    val nowPlayingMoviesGenre = data[GroupFilms.NOW_PLAYING]?.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    nowPlayingMoviesGenre?.let { movieGenreList.addAll(it) }
                }

                GroupFilms.UPCOMING -> {
                    val upComingMoviesLocalList = data[GroupFilms.UPCOMING]?.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            GroupFilms.UPCOMING.title
                        )
                    }
                    upComingMoviesLocalList?.let {
                        moviesLocalGroupList[GroupFilms.UPCOMING] = it //.add(it) to it) //.add(it)
                    }
                    val upComingMoviesGenre = data[GroupFilms.UPCOMING]?.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    upComingMoviesGenre?.let { movieGenreList.addAll(it) }
                }

                GroupFilms.POPULAR -> {
                    val popularMoviesLocalList = data[GroupFilms.POPULAR]?.map { movie ->
                        fromApiToMovieDomain(
                            movie,
                            GroupFilms.POPULAR.title
                        )
                    }
                    popularMoviesLocalList?.let {
                        moviesLocalGroupList[GroupFilms.POPULAR] = it //.add(it) to it) //.add(it)
                    }
                    val popularMoviesGenre = data[GroupFilms.POPULAR]?.map { movie ->
                        movie.genre_ids.map { genre ->
                            fromApiToMovieGenreDomain(genre, movie.id)
                        }
                    }
                    popularMoviesGenre?.let { movieGenreList.addAll(it) }
                }
            }
        }
    }

    private fun saveToStorage() {
        Completable.fromAction {
            _movieState.value.moviesLocalGroupList.forEach { it ->
                useCaseMovieFromStorage.invoke(it.value)
            }
            _movieState.value.movieGenreList.forEach { movieGenre ->
                useCaseMovieGenre.invoke(movieGenre)
            }
        }.applySchedulers()
            .doOnError { Log.d(TAG, "saveToStorage: $it") }
            .subscribe()
    }

    companion object {
        private const val TAG = "FeedViewModel"
    }
}