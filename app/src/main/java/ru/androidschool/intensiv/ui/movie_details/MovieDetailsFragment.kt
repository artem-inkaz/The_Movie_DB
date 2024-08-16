package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Response
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.moveid.MovieId
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.extensions.loadImageByUrl
import ru.androidschool.intensiv.extensions.voteAverage
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_MOVIE_ID
import timber.log.Timber
import kotlin.properties.Delegates

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {

    // Для загрузки из MovieList
    private var movieBundle by Delegates.notNull<Int>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MovieDetailsFragmentBinding {
        return MovieDetailsFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         arguments?.getInt(KEY_MOVIE_ID)?.let {
            movieBundle = arguments?.getInt(KEY_MOVIE_ID)!!
            movieBundle.let { movie ->
                showMovieDetail(movie)
            }
        }
    }

    private fun showMovieDetail(id: Int) = with(binding) {
        val getMovieDetails = MovieApiClient.apiClient.getMovieDetails(MovieId(id))
        getMovieDetails.enqueue(object : retrofit2.Callback<MoveIdResponse> {
            override fun onResponse(
                call: retrofit2.Call<MoveIdResponse>,
                response: Response<MoveIdResponse>
            ) {

                response.body()?.let {
                    title.text = it.title
                    movieRating.rating = voteAverage(it.voteAverage)
                    moveDescription.text = it.overview
                    it.backdropPath?.let { it1 -> movePoster.loadImageByUrl(it1) }
                }
            }

            override fun onFailure(call: retrofit2.Call<MoveIdResponse>, t: Throwable) {
                Timber.tag(TAG).e(t.toString())
            }
        })
    }

    companion object {
        const val TAG = "MovieDetailsFragment"
    }
}
