package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.data.ShowDest
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_TITLE
import ru.androidschool.intensiv.ui.feed.MovieItem

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private var _binding: TvShowsFragmentBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvShowsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showDestList =
            MockRepository.getShowDest().map {
                TvShowDestItem(it) { movie ->
                    openShowDest(
                        movie
                    )
                }
            }.toList()


        binding.moviesRecyclerView.adapter = adapter.apply { addAll(showDestList) }
    }

    private fun openShowDest(showDest: ShowDest) {
        val bundle = Bundle()
        bundle.putString(KEY_TITLE, showDest.title)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_TITLE = "title"
    }
}