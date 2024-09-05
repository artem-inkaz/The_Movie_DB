package ru.androidschool.intensiv.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder
import ru.androidschool.intensiv.databinding.FragmentWatchlistBinding
import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import timber.log.Timber

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding>() {

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

        getFavouritesFilm()
    }

    private fun getFavouritesFilm() = with(binding) {
        val movieList = RepositoryHolder.repositoryMovie().getFavouriteMovies()
        disposables.add(
            movieList
                .applySchedulers()
                .subscribe(
                    {
                        val moviesList = it.map { movie ->
                            MoviePreviewItem(
                                movie
                            ) { movie -> openMovieDetails(movie) }
                        }.distinct().toList()
                        adapter.clear()
                        moviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }
                    },
                    {
                        Timber.tag(TAG).d("Error doOnError: %s", it.message)
                    }
                )
        )
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
