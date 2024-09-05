package ru.androidschool.intensiv.ui.profile

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.base.BaseFragment
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder
import ru.androidschool.intensiv.databinding.FragmentProfileBinding
import ru.androidschool.intensiv.extensions.applySchedulers
import timber.log.Timber

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private lateinit var profileTabLayoutTitles: Array<String>

    private var countFavourite = 0
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if (position == 0) {
                getFavouritesFilm()
            } else {
                countFavourite = 0
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(R.drawable.ic_avatar)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_avatar)
            .into(binding.avatar)

        profileTabLayoutTitles = resources.getStringArray(R.array.tab_titles)

        val profileAdapter = ProfileAdapter(
            this,
            profileTabLayoutTitles.size
        )

        binding.profileViewPager.adapter = profileAdapter

        binding.profileViewPager.registerOnPageChangeCallback(
            profilePageChangeCallback
        )
    }

    private fun getFavouritesFilm() = with(binding) {
        val movieList = RepositoryHolder.repositoryMovie().getFavouriteMovies()
        disposables.add(
            movieList
                .applySchedulers()
                .subscribe(
                    {
                        countFavourite = it.size
                        TabLayoutMediator(
                            tabLayout,
                            profileViewPager
                        ) { tab, position ->
                            // Выделение первой части заголовка таба
                            // Название таба
                            var title = profileTabLayoutTitles[position]
                            title =
                                "${countFavourite} ${title.substring(title.lastIndexOf("\n") + 1)}"
                            // Раздеряем название на части. Первый элемент будет кол-во
                            val parts = title.split(" ")
                            val number = parts[0]
                            val spannableStringTitle = SpannableString(title)
                            spannableStringTitle.setSpan(RelativeSizeSpan(2f), 0, number.count(), 0)

                            tab.text = spannableStringTitle
                        }.attach()

                    },
                    {
                        Timber.tag(TAG).d("Error doOnError: %s", it.message)
                    }
                )
        )
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}
