package ru.androidschool.intensiv.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.core.base.BaseFragment
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.databinding.FragmentWatchlistBinding
import ru.androidschool.intensiv.presentation.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.presentation.profile.mvi.UserIntention
import ru.androidschool.intensiv.presentation.profile.mvi.ViewState
import ru.androidschool.intensiv.presentation.profile.viewmodel.ProfileViewModel
import ru.androidschool.intensiv.presentation.profile.viewmodel.ProfileViewModelFactory

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding>() {

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModelFactory() }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWatchlistBinding {
        return FragmentWatchlistBinding.inflate(inflater, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesRecyclerView.layoutManager = GridLayoutManager(context, 4)
        binding.moviesRecyclerView.adapter = adapter.apply { addAll(listOf()) }

        viewModel.observableState.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }

        viewModel.dispatch(UserIntention.LoadMovies)
    }

    private fun renderState(state: ViewState) {
        with(state) {
            when {
                isLoading -> renderLoadingState()
                isError -> renderErrorState()
                else -> renderMovieState(items)
            }
        }
    }

    private fun renderLoadingState() {
        // Show Progress Bar
        hideAll()
    }

    private fun renderErrorState() {
        viewModel.dispatch(UserIntention.ErrorShown)
    }

    private fun renderMovieState(items: List<MovieLocal>) = with(binding) {
        hideAll()
        val moviesList = items.map { movie ->
            MoviePreviewItem(
                movie
            ) { movie -> openMovieDetails(movie) }
        }.distinct().toList()
        adapter.clear()
        moviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }
        moviesRecyclerView.visibility = View.VISIBLE
    }

    private fun hideAll() = with(binding) {
        moviesRecyclerView.visibility = View.GONE
    }

    private fun openMovieDetails(movie: MovieLocal) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_MOVIE_ID, movie)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    companion object {
        const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
