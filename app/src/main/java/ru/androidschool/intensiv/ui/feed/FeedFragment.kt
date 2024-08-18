package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.extensions.getMoviesGroupList
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private var _binding: FeedFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
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
    private var disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
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
        disposables.addAll(
            getNowPlayingMovies
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Log.d(TAG, "Error: Какая-то ошибка") }
                .subscribe(
                    { result ->
                        val moviesList = getMoviesGroupList(
                            title = R.string.now_playing,
                            results = result?.results,
                            openMovieDetails = { openMovieDetails(it) }
                        )
                        moviesList?.let { moviesGroupList.add(it) }
                    },
                    {},
                )
        )

        val getUpComingMovies = MovieApiClient.apiClient.getUpComingMovies()
        disposables.addAll(
            getUpComingMovies
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        val moviesList = getMoviesGroupList(
                            title = R.string.upcoming,
                            results = result?.results,
                            openMovieDetails = { openMovieDetails(it) }
                        )
                        moviesList?.let { moviesGroupList.add(it) }
                    },
                    {},
                )
        )

        val getPopularMovies = MovieApiClient.apiClient.getPopularMovies()
        disposables.addAll(
            getPopularMovies
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        val moviesList = getMoviesGroupList(
                            title = R.string.popular,
                            results = result?.results,
                            openMovieDetails = { openMovieDetails(it) }
                        )
                        moviesList?.let { moviesGroupList.add(it) }
                        binding.moviesRecyclerView.adapter = adapter.apply {
                            addAll(moviesGroupList)
                        }
                    },
                    {},
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
        disposables.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_MOVIE_ID = "id"
        const val KEY_SEARCH = "search"
        const val TAG = "FeedFragment"
    }
}
