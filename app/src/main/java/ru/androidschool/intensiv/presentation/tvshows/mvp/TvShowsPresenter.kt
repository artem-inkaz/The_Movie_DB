package ru.androidschool.intensiv.presentation.tvshows.mvp

import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import javax.inject.Inject

class TvShowsPresenter @Inject constructor(
    private val useCase: TvShowsUseCase
) : TvShowsContract.Presenter() {
    override suspend fun makeList() {
        try {
            useCase.invoke()
                .onEach { view.showTvSeries(it) }
                .onCompletion { view.hideProgress() }
                .collect {
                    view.hideProgress()
                }
        } catch (e: Exception) {
            view.showErrorMessage(e.message)
            view.hideProgress()
        }
    }

    override suspend fun refreshList() {
        view.refresh()
        makeList()
    }
}