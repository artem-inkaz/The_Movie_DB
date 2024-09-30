package ru.androidschool.intensiv.presentation.tvshows.mvp

import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.presentation.base.BaseContract

class TvShowsContract {
    interface View : BaseContract.View {
        fun showTvSeries(data: List<TvShowsLocal>)
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
    }
    abstract class Presenter: BaseContract.Presenter<View>() {
        abstract suspend fun makeList()
        abstract suspend fun refreshList()
    }
}