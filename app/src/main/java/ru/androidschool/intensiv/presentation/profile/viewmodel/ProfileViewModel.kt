package ru.androidschool.intensiv.presentation.profile.viewmodel

import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.profile.mvi.UserIntention
import ru.androidschool.intensiv.presentation.profile.mvi.UserIntentionResult
import ru.androidschool.intensiv.presentation.profile.mvi.ViewState

class ProfileViewModel(
    private val repository: MovieStorageRepository
) : BaseViewModel<UserIntention, ViewState>() {

    override val initialState = ViewState()

    private val reducer: Reducer<ViewState, UserIntentionResult> = { state, change ->
        when (change) {
            is UserIntentionResult.Loading -> state.copy(isLoading = true)
            is UserIntentionResult.ListLoaded -> state.copy(
                isLoading = false,
                items = change.items,
            )

            is UserIntentionResult.Error -> state.copy(
                isLoading = false,
                isError = true,
            )

            is UserIntentionResult.ClearError -> state.copy(isError = false)
        }
    }

    init {
        bindActions()
    }

    private fun bindActions() {
        val itemsLoadedUserIntentionResult = actions.ofType(UserIntention.LoadMovies::class.java)
            .switchMap {
                repository.getFavouriteMovies()
                    .applySchedulers()
                    .toObservable()
                    .map<UserIntentionResult> { UserIntentionResult.ListLoaded(it) }
                    .defaultIfEmpty(UserIntentionResult.ListLoaded(emptyList()))
                    .onErrorReturn { UserIntentionResult.Error }
                    .startWith(UserIntentionResult.Loading)
            }

        val errorShownUserIntentionResult = actions.ofType(UserIntention.ErrorShown::class.java)
            .map { UserIntentionResult.ClearError }

        val allChanges = Observable.merge(
            itemsLoadedUserIntentionResult,
            errorShownUserIntentionResult,
        )

        disposables.add(
            allChanges
                .scan(initialState, reducer)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(state::setValue) { println("Error $it") }
        )
    }
}