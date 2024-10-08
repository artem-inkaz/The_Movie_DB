package ru.androidschool.intensiv.presentation.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Completable
import ru.androidschool.intensiv.appComponent
import ru.androidschool.intensiv.core.base.BaseFragment
import ru.androidschool.intensiv.data.datasource.api.actor.ActorsDataSource
import ru.androidschool.intensiv.data.datasource.api.moviedetail.MovieDetailDataSource
import ru.androidschool.intensiv.core.network.dto.moveid.MovieId
import ru.androidschool.intensiv.core.network.utils.voteAverage
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.data.mappers.GenreMapperDto
import ru.androidschool.intensiv.data.vo.Actor
import ru.androidschool.intensiv.data.vo.Genre
import ru.androidschool.intensiv.data.vo.MovieActor
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.domain.datasource.ActorStorageRepository
import ru.androidschool.intensiv.domain.datasource.GenreStorageRepository
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import ru.androidschool.intensiv.domain.repository.MovieActorRepository
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.extensions.loadImageByUrl

import ru.androidschool.intensiv.presentation.feed.FeedFragment.Companion.KEY_MOVIE_ID
import ru.androidschool.intensiv.presentation.tvshows.TvShowsFragment
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {

    @Inject
    lateinit var repositoryMovieDetail: MovieDetailDataSource

    @Inject
    lateinit var repositoryFromStorageGenre: GenreStorageRepository

    @Inject
    lateinit var repositoryMovieActor: MovieActorRepository

    @Inject
    lateinit var repositoryFromStorageActor: ActorStorageRepository

    @Inject
    lateinit var actorsDataSource: ActorsDataSource

    @Inject
    lateinit var repositoryMovieFromStorage: MovieStorageRepository

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    // Для загрузки из MovieList
    private var movieIdBundle by Delegates.notNull<MovieLocal>()
    private lateinit var movieDetail: MovieLocal

    override fun onAttach(context: Context) {
        requireActivity().appComponent().inject(this)
        super.onAttach(context)
    }

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
        val getMovieDetails = repositoryMovieDetail.getMovieDetails(MovieId(movie.id))
        val genresDomainList = mutableListOf<Genre>()
        disposables.add(
            getMovieDetails
                .applySchedulers()
                .doFinally {
                    Completable.fromAction {
                        repositoryFromStorageGenre.addAll(genresDomainList)
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
        val getMovieActors = actorsDataSource.getActorsByMovieId(MovieId(moveId))
        disposables.add(
            getMovieActors
                .applySchedulers()
                .doFinally {
                    Completable.fromAction {
                        repositoryFromStorageActor.addAll(actorsDomainList)
                        repositoryMovieActor.addAll(movieActorsDomainList)
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
                        repositoryMovieFromStorage.create(movie)
                    }.applySchedulers()
                        .subscribe()
                )
            }
        }
    }

    private fun checkFavouriteMovieFromStorage(moveId: Int) {
        val getMovieFavourite = repositoryMovieFromStorage.getFavouriteMoviesById((moveId))
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