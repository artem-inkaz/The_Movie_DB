package ru.androidschool.intensiv.ui.feed

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.ItemWithTextBinding
import ru.androidschool.intensiv.domain.MovieLocal
import ru.androidschool.intensiv.extensions.loadImageByUrl
import ru.androidschool.intensiv.extensions.voteAverage

class MovieItem(
    private val content: MovieLocal,
    private val onClick: (movie: MovieLocal) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout(): Int = R.layout.item_with_text

    override fun bind(view: ItemWithTextBinding, position: Int) {
        view.description.text = content.title
        view.movieRating.rating = voteAverage(content.voteAverage)
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        content.backdropPath?.let { view.imagePreview.loadImageByUrl(it) }
    }

    override fun initializeViewBinding(v: View) = ItemWithTextBinding.bind(v)
}
