package ru.androidschool.intensiv.presentation.feed

import android.view.View
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.ItemCardBinding

class MainCardContainer(
    private val title: String,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemCardBinding>() {

    override fun getLayout() = R.layout.item_card

    override fun bind(view: ItemCardBinding, position: Int) {
        view.titleTextView.text = title
        view.itemsContainer.adapter = GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }

    override fun initializeViewBinding(p0: View): ItemCardBinding = ItemCardBinding.bind(p0)
}
