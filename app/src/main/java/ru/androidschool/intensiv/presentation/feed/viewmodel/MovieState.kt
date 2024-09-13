package ru.androidschool.intensiv.presentation.feed.viewmodel

import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.data.vo.MovieLocal

data class MovieState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val moviesLocalGroupList: MutableList<List<MovieLocal>> = mutableListOf<List<MovieLocal>>(),
    val movieGenreList: MutableList<List<MovieGenre>> = mutableListOf<List<MovieGenre>>(),
){
    companion object {
        val initialState = MovieState()
    }
}
