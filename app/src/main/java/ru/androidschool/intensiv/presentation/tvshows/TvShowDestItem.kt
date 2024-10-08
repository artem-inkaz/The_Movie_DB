package ru.androidschool.intensiv.presentation.tvshows

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.core.network.utils.voteAverage
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.databinding.ItemTvShowsDestBinding
import ru.androidschool.intensiv.extensions.loadImageByUrl


class TvShowDestItem(
    private val content: TvShowsLocal,
    private val onClick: (showdest: TvShowsLocal) -> Unit
) : BindableItem<ItemTvShowsDestBinding>() {

    override fun getLayout(): Int = R.layout.item_tv_shows_dest

    override fun bind(view: ItemTvShowsDestBinding, position: Int) {
        view.description.text = content.name
        view.movieRating.rating = voteAverage(content.voteAverage)
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        content.backdropPath?.let { view.imagePreview.loadImageByUrl(it) }
    }

    override fun initializeViewBinding(v: View) = ItemTvShowsDestBinding.bind(v)
}
