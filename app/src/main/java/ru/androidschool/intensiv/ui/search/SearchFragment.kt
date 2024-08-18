package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MovieLocal
import ru.androidschool.intensiv.data.mappers.MovieSearchMapper
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.databinding.FragmentSearchBinding
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.afterTextChanged
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.feed.MovieItem
import java.util.Locale
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
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
    var movieList: Set<MovieItem> = setOf()
    private var disposables = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        searchBinding.searchToolbar.setText(searchTerm)
        start()
        search()
        clear()
    }

    private fun start() {
        disposables.add(
            PublishSubject.create(ObservableOnSubscribe<String> { subscriber ->
                subscriber.onNext(searchBinding.searchToolbar.binding.searchEditText.text.toString())
            })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    binding.moviesRecyclerView.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotBlank() && text.length >= 3 }
                .map { text -> text.lowercase(Locale.getDefault()).trim() }
                .distinctUntilChanged()
                .doOnError { Log.d(TAG, "Error: Какая-то ошибка") }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .retry()
                .subscribe(
                    { query ->
                        val findMovies = MovieApiClient.apiClient.findMovies(query)
                        disposables.add(
                            findMovies
                                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    { result ->
                                        Log.d("SSSS", "Error subscribe: ${result.results}")
                                        movieList =
                                            result.results.distinct().map {
                                                MovieItem(MovieSearchMapper().toViewObject(it)) { movie ->
                                                    openMovieDetails(
                                                        movie
                                                    )
                                                }
                                            }.toSet()
                                        Log.d("SSSS", "movieList.size subscribe: ${movieList.size}")
                                        binding.moviesRecyclerView.adapter = adapter.apply {
                                            addAll(movieList)
                                        }
                                    }, {}
                                )
                        )
                    },
                    { error -> Log.d(TAG, "Error: ${error.message}") }
                )
        )
    }

    private fun search() = with(searchBinding) {
        disposables.add(
            PublishSubject.create(ObservableOnSubscribe<String> { subscriber ->
                searchToolbar.binding.searchEditText.afterTextChanged {
                    subscriber.onNext(it.toString())
                    if (it.toString().isEmpty()) {
                        binding.moviesRecyclerView.adapter = adapter.apply {
                            clear()
                        }
                        movieList = setOf()
                        binding.progress.visibility = View.GONE
                    }
                }
            })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .doOnNext {
                    binding.moviesRecyclerView.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotBlank() && text.length >= 3 }
                .map { text -> text.lowercase(Locale.getDefault()).trim() }
                .distinctUntilChanged()
                .doOnError { Log.d(TAG, "Error: Какая-то ошибка") }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach {
                    binding.progress.visibility = View.GONE
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                }
                .retry()
                .subscribe(
                    { query ->
                        val findMovies = MovieApiClient.apiClient.findMovies(query.toString())
                        disposables.add(
                            findMovies
                                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    { result ->
                                        Log.d("SSSS", "Error subscribe: ${result.results}")
                                        movieList =
                                            result.results.distinct().map {
                                                MovieItem(MovieSearchMapper().toViewObject(it)) { movie ->
                                                    openMovieDetails(
                                                        movie
                                                    )
                                                }
                                            }.toSet()
                                        Log.d("SSSS", "movieList.size subscribe: ${movieList.size}")
                                        binding.moviesRecyclerView.adapter = adapter.apply {
                                            addAll(movieList)
                                        }
                                    }, {}
                                )
                        )
                    },
                    { error -> Log.d(TAG, "Error: ${error.message}") }
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

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    companion object {
        const val TAG = "SearchFragment"
    }
}
