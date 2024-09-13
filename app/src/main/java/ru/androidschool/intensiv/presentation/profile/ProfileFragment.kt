package ru.androidschool.intensiv.presentation.profile

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.core.base.BaseFragment
import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.databinding.FragmentProfileBinding
import ru.androidschool.intensiv.presentation.profile.mvi.UserIntention
import ru.androidschool.intensiv.presentation.profile.mvi.ViewState
import ru.androidschool.intensiv.presentation.profile.viewmodel.ProfileViewModel
import ru.androidschool.intensiv.presentation.profile.viewmodel.ProfileViewModelFactory

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels { ProfileViewModelFactory() }

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
                viewModel.observableState.observe(viewLifecycleOwner) { state ->
                    renderState(state)
                }
                viewModel.dispatch(UserIntention.LoadMovies)
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

    private fun renderState(state: ViewState) {
        with(state) {
            when {
                isLoading -> renderLoadingState()
                isError -> renderErrorState()
                else -> renderMoviesState(items)
            }
        }
    }

    private fun renderLoadingState() {
        // Show Progress Bar
    }

    private fun renderErrorState() {
        viewModel.dispatch(UserIntention.ErrorShown)
    }

    private fun renderMoviesState(items: List<MovieLocal>) = with(binding) {
        countFavourite = items.size
        TabLayoutMediator(tabLayout, profileViewPager) { tab, position ->
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
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}
