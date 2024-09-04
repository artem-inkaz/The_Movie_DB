package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.mappers.TvShowsMapper
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.TvShowsLocal
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.network.MovieApiClient
import timber.log.Timber

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {

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

        val getPopularTvShows = MovieApiClient.apiClient.getPopularTvShows()
        disposables.add(
            getPopularTvShows
                .applySchedulers()
                .doOnSubscribe {
                    binding.moviesRecyclerView.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                .doFinally {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .doOnError { Timber.tag(TAG).d("doOnError: %s", it.message) }
                .subscribe(
                    { result ->
                        val tvShowsList =
                            result.results.map {
                                TvShowDestItem(TvShowsMapper().toViewObject(it)) { tvShows ->
                                    openShowDest(
                                        tvShows
                                    )
                                }
                            }.toList()
                        binding.moviesRecyclerView.adapter = adapter.apply {
                            if (tvShowsList != null) {
                                addAll(tvShowsList)
                            }
                        }
                    },
                    { Timber.tag(TAG).d("Error subscribe: %s", it.message) },
                )
        )
    }

    private fun openShowDest(showDest: TvShowsLocal) {
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, showDest.name)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    companion object {
        const val TAG = "TvShowsFragment"
        const val KEY_TITLE = "title"
    }
}