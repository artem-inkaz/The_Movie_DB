package ru.androidschool.intensiv.presentation.tvshows.mvp

import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import javax.inject.Inject

class TvShowsPresenter @Inject constructor(
    private val useCase: TvShowsUseCase
) : TvShowsContract.Presenter() {
    override fun makeList() {
        subscribe(useCase.invoke()
            .doOnNext { view.showTvSeries(it) }
            .doOnComplete { view.hideProgress() }
            .subscribe(
                {
                    view.hideProgress()
                },
                {
                    view.showErrorMessage(it.message)
                    view.hideProgress()
                }
            )

        )
    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}