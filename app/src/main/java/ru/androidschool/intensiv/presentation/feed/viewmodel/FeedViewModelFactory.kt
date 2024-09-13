package ru.androidschool.intensiv.presentation.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.repositoryNowPlayingMovie
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.repositoryPopularMovie
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.repositoryUpCommingMovie
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.useCaseMovieFromStorage
import ru.androidschool.intensiv.data.repositoryimpl.RepositoryHolder.useCaseMovieGenre

class FeedViewModelFactory  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        FeedViewModel::class.java -> FeedViewModel(
            repositoryNowPlayingMovie = repositoryNowPlayingMovie(),
            repositoryPopularMovie =  repositoryPopularMovie(),
            repositoryUpCommingMovie = repositoryUpCommingMovie(),
            useCaseMovieGenre = useCaseMovieGenre(),
            useCaseMovieFromStorage = useCaseMovieFromStorage()
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}