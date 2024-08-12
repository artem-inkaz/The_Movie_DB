package ru.androidschool.intensiv.ui.tvshows

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.ShowDest
import ru.androidschool.intensiv.databinding.ItemTvShowsDestBinding
import ru.androidschool.intensiv.extensions.loadImageByUrl

class TvShowDestItem(
    private val content: ShowDest,
    private val onClick: (showdest: ShowDest) -> Unit
) : BindableItem<ItemTvShowsDestBinding>() {

    override fun getLayout(): Int = R.layout.item_tv_shows_dest

    override fun bind(view: ItemTvShowsDestBinding, position: Int) {
        view.description.text = content.title
        view.movieRating.rating = content.rating
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        view.imagePreview.loadImageByUrl("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
    }

    override fun initializeViewBinding(v: View) = ItemTvShowsDestBinding.bind(v)
}
