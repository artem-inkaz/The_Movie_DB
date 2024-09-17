package ru.androidschool.intensiv.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<VIEW_MODEL : ViewModel> @Inject constructor(private val viewModel: Provider<VIEW_MODEL>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VIEW_MODEL : ViewModel> create(modelClass: Class<VIEW_MODEL>): VIEW_MODEL {
        return viewModel.get() as VIEW_MODEL
    }
}

inline fun <reified VIEW_MODEL : ViewModel> Fragment.withFactory(factory: ViewModelFactory<VIEW_MODEL>): VIEW_MODEL =
    ViewModelProvider(this, factory)[VIEW_MODEL::class.java]

inline fun <reified VIEW_MODEL : ViewModel> Fragment.withFactoryParentFragment(factory: ViewModelFactory<VIEW_MODEL>): VIEW_MODEL =
    ViewModelProvider(requireParentFragment(), factory)[VIEW_MODEL::class.java]

inline fun <reified VIEW_MODEL : ViewModel> FragmentActivity.withFactory(factory: ViewModelFactory<VIEW_MODEL>): VIEW_MODEL =
    ViewModelProvider(this, factory)[VIEW_MODEL::class.java]