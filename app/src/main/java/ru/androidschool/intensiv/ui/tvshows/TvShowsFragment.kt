package ru.androidschool.intensiv.ui.tvshows

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
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.data.ShowDest
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {

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
    ): TvShowsFragmentBinding {
        return TvShowsFragmentBinding.inflate(inflater, container, false)
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

    companion object {
        const val KEY_TITLE = "title"
    }
}