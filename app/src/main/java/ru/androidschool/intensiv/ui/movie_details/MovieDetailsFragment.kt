package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Completable
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.data.mappers.GenreMapperDto
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder
import ru.androidschool.intensiv.data.response.moveid.MovieId
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.domain.Genre
import ru.androidschool.intensiv.domain.MovieActor
import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.extensions.loadImageByUrl
import ru.androidschool.intensiv.extensions.voteAverage
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.ui.tvshows.TvShowsFragment
import timber.log.Timber
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    // Для загрузки из MovieList
    private var movieIdBundle by Delegates.notNull<MovieLocal>()
    lateinit var movieDetail: MovieLocal

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MovieDetailsFragmentBinding {
        return MovieDetailsFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(KEY_MOVIE_ID)?.let {
            movieIdBundle = arguments?.getParcelable<MovieLocal>(KEY_MOVIE_ID)!!
            movieIdBundle.let { movie ->
                movieDetail = movie
                showMovieDetail(movie)
                showActors(movie.id)
                checkFavouriteMovieFromStorage(movie.id)
            }
        }
        checkFavouriteMovie()
    }

    private fun showMovieDetail(movie: MovieLocal) = with(binding) {
        val getMovieDetails = MovieApiClient.apiClient.getMovieDetails(MovieId(movie.id))
        val genresDomainList = mutableListOf<Genre>()
        disposables.add(
            getMovieDetails
                .applySchedulers()
                .doFinally {
                    Completable.fromAction {
                        RepositoryHolder.repositoryGenre().addAll(genresDomainList)
                    }.applySchedulers()
                        .subscribe()
                }
                .subscribe(
                    { result ->
                        result?.let { it ->
                            genresDomainList.addAll(it.genres.map {
                                GenreMapperDto().toViewObject(it)
                            })

                            title.text = it.title
                            movieRating.rating = voteAverage(it.voteAverage)
                            moveDescription.text = it.overview
                            it.backdropPath?.let { it1 -> movePoster.loadImageByUrl(it1) }
                            movieDetail = MovieLocal(
                                id = it.id,
                                title = it.title,
                                overview = it.overview,
                                voteAverage = it.voteAverage,
                                backdropPath = it.backdropPath,
                                posterPath = it.posterPath,
                                like = movie.like,
                                movieGroup = movie.movieGroup
                            )
                        }
                    },
                    { Timber.tag(TAG).d("Error subscribe : %s", it.message) }
                )
        )
    }

    private fun showActors(moveId: Int) {
        val actorsDomainList = mutableListOf<Actor>()
        val movieActorsDomainList = mutableListOf<MovieActor>()
        val getMovieActors = MovieApiClient.apiClient.getMovieIdCredits(MovieId(moveId))
        disposables.add(
            getMovieActors
                .applySchedulers()
                .doFinally {
                    Completable.fromAction {
                        RepositoryHolder.repositoryActor().addAll(actorsDomainList)
                        RepositoryHolder.repositoryMovieActor().addAll(movieActorsDomainList)
                    }.applySchedulers()
                        .subscribe()
                }
                .subscribe(
                    { result ->
                        actorsDomainList.addAll(result.cast.map {
                            ActorMapperDto().toViewObject(it)
                        })
                        movieActorsDomainList.addAll(actorsDomainList.map {
                            MovieActor(
                                movieId = movieDetail.id,
                                actorId = it.id
                            )
                        })

                        val actorsList =
                            actorsDomainList.map {
                                MovieActorItem(it) {
                                }
                            }
                        binding.listActors.moviesActorsListView.adapter = adapter.apply {
                            if (actorsList != null) {
                                addAll(actorsList)
                            }
                        }
                    },
                    { Timber.tag(TvShowsFragment.TAG).d("Error subscribe: %s", it.message) }
                ))
    }

    private fun checkFavouriteMovie() = with(binding) {
        movieFavourite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (::movieDetail.isInitialized) {
                val movie = MovieLocal(
                    id = movieDetail.id,
                    title = movieDetail.title,
                    overview = movieDetail.overview,
                    voteAverage = movieDetail.voteAverage,
                    backdropPath = movieDetail.backdropPath,
                    posterPath = movieDetail.posterPath,
                    like = isChecked,
                    movieGroup = movieDetail.movieGroup
                )
                disposables.add(
                    Completable.fromAction {
                        RepositoryHolder.repositoryMovie().create(movie)
                    }.applySchedulers()
                        .subscribe()
                )
            }
        }
    }

    private fun checkFavouriteMovieFromStorage(moveId: Int) {
        val getMovieFavourite = RepositoryHolder.repositoryMovie().getFavouriteMoviesById((moveId))
        disposables.add(
            getMovieFavourite
                .applySchedulers()
                .subscribe(
                    { result ->
                        binding.movieFavourite.isChecked = result
                    },
                    { Timber.tag(TvShowsFragment.TAG).d("Error subscribe: %s", it.message) }
                ))
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
    }
}