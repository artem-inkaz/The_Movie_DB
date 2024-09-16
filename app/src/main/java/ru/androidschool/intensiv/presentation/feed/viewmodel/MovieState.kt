package ru.androidschool.intensiv.presentation.feed.viewmodel

import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.presentation.feed.GroupFilms

data class MovieState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val moviesLocalGroupList: HashMap<GroupFilms, List<MovieLocal>> = HashMap<GroupFilms, List<MovieLocal>>(),
    val movieGenreList: MutableList<List<MovieGenre>> = mutableListOf<List<MovieGenre>>(),
){
    companion object {
        val initialState = MovieState()
    }
}
