package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.TvShowsLocal
import ru.androidschool.intensiv.data.mappers.TvShowsMapper
import ru.androidschool.intensiv.data.tvseries.TvShowsResponse
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
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
        getPopularTvShows.enqueue(object : retrofit2.Callback<TvShowsResponse> {
            override fun onResponse(
                call: retrofit2.Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val tvShowsList =
                    response.body()?.results?.map {
                        TvShowDestItem(TvShowsMapper().toView(it)) { tvShows ->
                            openShowDest(
                                tvShows
                            )
                        }
                    }?.toList()

                binding.moviesRecyclerView.adapter = adapter.apply {
                    if (tvShowsList != null) {
                        addAll(tvShowsList)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<TvShowsResponse>, t: Throwable) {
                Timber.tag(TAG).e(t.toString())
            }
        })
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