package ru.androidschool.intensiv.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.core.base.BaseFragment
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import ru.androidschool.intensiv.presentation.tvshows.mvp.TvShowsContract
import ru.androidschool.intensiv.presentation.tvshows.mvp.TvShowsPresenter
import timber.log.Timber

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>(), TvShowsContract.View {

    // Инициализируем
    private val presenter: TvShowsPresenter by lazy {
        TvShowsPresenter(TvShowsUseCase(RepositoryHolder.repositoryTvShows()))
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TvShowsFragmentBinding {
        return TvShowsFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем в presenter имплементацию FeedView
        presenter.attach(this)
        // Вызываем метод presenter для получения фильмов
        presenter.makeList()
    }

    private fun openShowDest(showDest: TvShowsLocal) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_TV_SHOWS_ID, showDest)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    override fun showTvSeries(data: List<TvShowsLocal>) {
        val tvShowsList = data.map {
            TvShowDestItem(it) { tvShows ->
                //временно убрал т.к. логика работает для Movie сейчас
//                openShowDest(tvShows)
            }
        }.toList()
        binding.moviesRecyclerView.adapter = adapter.apply {
            if (tvShowsList != null) {
                addAll(tvShowsList)
            }
        }
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun showErrorMessage(error: String?) {
        Timber.tag(TAG).d("Error subscribe: %s", error)
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    companion object {
        const val TAG = "TvShowsFragment"
        const val KEY_TITLE = "title"
        const val KEY_TV_SHOWS_ID = "id"
    }
}