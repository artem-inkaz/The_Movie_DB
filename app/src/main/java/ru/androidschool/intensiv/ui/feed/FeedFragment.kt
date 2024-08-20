package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Single
import io.reactivex.functions.Function3
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.extensions.getMoviesGroupList
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : BaseFragment<FeedFragmentBinding>() {

    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val searchBinding get() = _searchBinding!!

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
    ): FeedFragmentBinding {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(_binding!!.root)
        return _binding!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }
        val moviesGroupList = mutableListOf<MainCardContainer>()

        val getNowPlayingMovies = MovieApiClient.apiClient.getNowPlayingMovies()
        val getUpComingMovies = MovieApiClient.apiClient.getUpComingMovies()
        val getPopularMovies = MovieApiClient.apiClient.getPopularMovies()

        disposables.add(
            Single.zip(getNowPlayingMovies, getUpComingMovies, getPopularMovies,
                Function3 { nowPlayingMovies, upComingMovies, popularMovies ->
                    val nowPlayingMoviesList = getMoviesGroupList(
                        title = R.string.now_playing,
                        results = nowPlayingMovies.results,
                        openMovieDetails = { openMovieDetails(it) }
                    )
                    nowPlayingMoviesList?.let { moviesGroupList.add(it) }
                    val upComingMoviesList = getMoviesGroupList(
                        title = R.string.upcoming,
                        results = upComingMovies.results,
                        openMovieDetails = { openMovieDetails(it) }
                    )
                    upComingMoviesList?.let { moviesGroupList.add(it) }
                    val popularMoviesList = getMoviesGroupList(
                        title = R.string.popular,
                        results = popularMovies.results,
                        openMovieDetails = { openMovieDetails(it) }
                    )
                    popularMoviesList?.let { moviesGroupList.add(it) } == true

                })
                .applySchedulers()
                .doOnSubscribe {
                    binding.moviesRecyclerView.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                .doFinally {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .doOnError {
                    Timber.tag(TAG).d("Error doOnError: %s", it.message) }
                .subscribe(
                    {
                        binding.moviesRecyclerView.adapter = adapter.apply {
                            addAll(moviesGroupList)
                        }
                    },
                    {
                        binding.moviesRecyclerView.visibility = View.VISIBLE
                        binding.progress.visibility = View.GONE
                        Timber.tag(TAG).d("Error subscribe : %s", it.message)
                    }
                )
        )
    }

    private fun openMovieDetails(movie: MovieLocal) {
        val bundle = Bundle()
        bundle.putInt(KEY_MOVIE_ID, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
        binding.moviesRecyclerView.adapter = adapter.apply {
            clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_MOVIE_ID = "id"
        const val KEY_SEARCH = "search"
        const val TAG = "FeedFragment"
    }
}
