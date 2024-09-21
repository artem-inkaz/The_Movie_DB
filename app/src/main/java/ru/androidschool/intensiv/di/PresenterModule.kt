package ru.androidschool.intensiv.di

import dagger.Module
import dagger.Provides
import ru.androidschool.intensiv.domain.usecase.TvShowsUseCase
import ru.androidschool.intensiv.presentation.tvshows.mvp.TvShowsPresenter
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @ApplicationScope
    fun provideTvShowsPresenter(useCase: TvShowsUseCase): TvShowsPresenter =
        TvShowsPresenter(useCase)
}