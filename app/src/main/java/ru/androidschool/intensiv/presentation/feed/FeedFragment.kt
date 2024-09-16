package ru.androidschool.intensiv.presentation.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.core.base.BaseFragment
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.extensions.getMoviesGroupList
import ru.androidschool.intensiv.presentation.afterTextChanged
import ru.androidschool.intensiv.presentation.feed.viewmodel.FeedViewModel
import ru.androidschool.intensiv.presentation.feed.viewmodel.FeedViewModelFactory
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

    private val viewModel: FeedViewModel by viewModels { FeedViewModelFactory() }

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
        initViewModel()
        initSearch()
    }

    private fun initSearch() {
        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }
    }

    private fun initViewModel() {
        val moviesGroupList = mutableListOf<MainCardContainer>()
        doInScopeResume {
            viewModel.movieState.collect { movieState ->
                when (movieState.isLoading) {
                    true -> {
                        binding.moviesRecyclerView.visibility = View.GONE
                        binding.progress.visibility = View.VISIBLE
                    }

                    false -> {
                        binding.progress.visibility = View.GONE
                        binding.moviesRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
        doInScopeResume {
            viewModel.movieState.collect { movieState ->
                val moviesLocalGroupList = movieState.moviesLocalGroupList
                moviesLocalGroupList.forEach { movieLocal ->
                    when (movieLocal.key) {
                        GroupFilms.NOW_PLAYING -> {
                            val nowPlayingMoviesList = resolveTitle(
                                GroupFilms.NOW_PLAYING,
                                moviesLocalGroupList[GroupFilms.NOW_PLAYING]
                            )
                            if (nowPlayingMoviesList != null) {
                                moviesGroupList.add(nowPlayingMoviesList)
                            }
                        }

                        GroupFilms.UPCOMING -> {
                            val upComingMoviesList = resolveTitle(
                                GroupFilms.UPCOMING,
                                moviesLocalGroupList[GroupFilms.UPCOMING]
                            )
                            if (upComingMoviesList != null) {
                                moviesGroupList.add(upComingMoviesList)
                            }
                        }

                        GroupFilms.POPULAR -> {
                            val popularMoviesList = resolveTitle(
                                GroupFilms.POPULAR,
                                moviesLocalGroupList[GroupFilms.POPULAR]
                            )
                            if (popularMoviesList != null) {
                                moviesGroupList.add(popularMoviesList)
                            }
                        }
                    }
                }
                binding.moviesRecyclerView.adapter = adapter.apply {
                    addAll(moviesGroupList)
                }
            }
        }
    }

    private fun resolveTitle(title: GroupFilms, results: List<MovieLocal>?): MainCardContainer? {
        return getMoviesGroupList(
            title = title.title,
            results = results,
            openMovieDetails = { openMovieDetails(it) })
    }

    private fun openMovieDetails(movie: MovieLocal) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_MOVIE_ID, movie)
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
