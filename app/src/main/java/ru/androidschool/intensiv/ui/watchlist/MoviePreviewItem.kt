package ru.androidschool.intensiv.ui.watchlist

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R

import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.databinding.ItemSmallBinding
import ru.androidschool.intensiv.extensions.loadImageByUrl

class MoviePreviewItem(
    private val content: MovieLocal,
    private val onClick: (movie: MovieLocal) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun bind(view: ItemSmallBinding, position: Int) {
        view.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }

        content.backdropPath?.let { view.imagePreview.loadImageByUrl(it) }
    }

    override fun initializeViewBinding(v: View): ItemSmallBinding = ItemSmallBinding.bind(v)
}
