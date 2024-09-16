package ru.androidschool.intensiv.presentation.profile.mvi

import com.ww.roxie.BaseAction
import com.ww.roxie.BaseState
import ru.androidschool.intensiv.data.vo.MovieLocal

data class ViewState(
    val items: List<MovieLocal> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
) : BaseState

sealed class UserIntentionResult {
    object Loading : UserIntentionResult()
    data class ListLoaded(val items: List<MovieLocal>) : UserIntentionResult()
    object Error : UserIntentionResult()
    object ClearError : UserIntentionResult()
}

sealed class UserIntention : BaseAction {
    object LoadMovies : UserIntention()
    object ErrorShown : UserIntention()
}