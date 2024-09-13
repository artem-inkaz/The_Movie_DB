package ru.androidschool.intensiv.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.repositoryMovieFromStorage

class ProfileViewModelFactory  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ProfileViewModel::class.java -> ProfileViewModel(
            repository = repositoryMovieFromStorage()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}