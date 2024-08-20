package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.data.mappers.MovieSearchResultItemMapper
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.databinding.FragmentSearchBinding
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.feed.MovieItem
import ru.androidschool.utils.Constants.MIN_LENGTH_WORD
import java.util.Locale
import java.util.concurrent.TimeUnit


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
        searchBinding.searchToolbar.setText(searchTerm)
        search()
        clear()
    }

    private fun search() = with(searchBinding) {
        disposables.add(
            searchBinding.searchToolbar.getTextWatcherObservable()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    binding.moviesRecyclerView.adapter = adapter.apply {
                        clear()
                    }
                    movieList = setOf()
                }
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotBlank() && text.length >= MIN_LENGTH_WORD }
                .map { text -> text.lowercase(Locale.getDefault()).trim() }
                .distinctUntilChanged()
                .doOnError { Log.d(TAG, "83 Error doOnError: ${it.message}") }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .observeOn(io.reactivex.schedulers.Schedulers.io())
                .switchMap { query -> MovieApiClient.apiClient.findMovies(query) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { result ->
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
                    binding.moviesRecyclerView.adapter = adapter.apply {
                        addAll(movieList)
                    }
                }
                .doFinally {
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE
                }
                .doOnError { Log.d(TAG, "Error doOnError : ${it.message}") }
                .subscribe()
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
