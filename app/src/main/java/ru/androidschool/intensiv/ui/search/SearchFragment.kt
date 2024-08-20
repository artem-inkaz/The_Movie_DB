package ru.androidschool.intensiv.ui.search

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
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.data.mappers.MovieSearchResultItemMapper
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.databinding.FragmentSearchBinding
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.feed.MovieItem
import timber.log.Timber

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

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
    var movieList: Set<MovieItem> = setOf()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(_binding!!.root)
        return _binding!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        search()
        searchBinding.searchToolbar.setText(searchTerm)
        clear()
    }

    private fun search() = with(searchBinding) {
        disposables.add(
            searchBinding.searchToolbar.onTextChanged()
                .switchMap { query -> MovieApiClient.apiClient.findMovies(query) }
                .applySchedulers()
                .doOnEach {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .subscribe({ result ->
                    movieList =
                        result.results.distinct().map {
                            MovieItem(
                                MovieSearchResultItemMapper().toViewObject(
                                    it
                                )
                            ) { movie ->
                                openMovieDetails(
                                    movie
                                )
                            }
                        }.toSet()
                    adapter.clear()
                    binding.moviesRecyclerView.adapter = adapter.apply {
                        addAll(movieList)
                    }
                },
                    { Timber.e("Error: ${it.message}") }
                )
        )
    }

    private fun clear() {
        searchBinding.searchToolbar.binding.deleteTextButton.setOnClickListener {
            searchBinding.searchToolbar.binding.searchEditText.text.clear()
            binding.moviesRecyclerView.adapter = adapter.apply {
                clear()
            }
            binding.progress.visibility = View.GONE
            movieList = setOf()
        }
    }

    private fun openMovieDetails(movie: MovieLocal) {
        val bundle = Bundle()
        bundle.putInt(KEY_MOVIE_ID, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}
