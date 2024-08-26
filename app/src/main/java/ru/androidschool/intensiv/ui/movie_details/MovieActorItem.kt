package ru.androidschool.intensiv.ui.movie_details

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.ItemActorBinding
import ru.androidschool.intensiv.domain.Actor
import ru.androidschool.intensiv.extensions.loadImageByUrl

class MovieActorItem(
    private val content: Actor,
    private val onClick: (actor: Actor) -> Unit
) : BindableItem<ItemActorBinding>() {

    override fun getLayout(): Int = R.layout.item_actor

    override fun bind(view: ItemActorBinding, position: Int) {
        view.description.text = content.name
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        content.profile_path?.let {
            view.imagePreview.loadImageByUrl(it)
        }
    }

    override fun initializeViewBinding(v: View) = ItemActorBinding.bind(v)
}
