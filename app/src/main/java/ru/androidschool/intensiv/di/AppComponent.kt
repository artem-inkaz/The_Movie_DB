package ru.androidschool.intensiv.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.androidschool.intensiv.MainActivity
import ru.androidschool.intensiv.core.network.di.NetworkComponent
import ru.androidschool.intensiv.presentation.feed.FeedFragment
import ru.androidschool.intensiv.presentation.movie_details.MovieDetailsFragment
import ru.androidschool.intensiv.presentation.profile.ProfileFragment
import ru.androidschool.intensiv.presentation.search.SearchFragment
import ru.androidschool.intensiv.presentation.tvshows.TvShowsFragment
import ru.androidschool.intensiv.presentation.watchlist.WatchlistFragment
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention
annotation class ApplicationScope

@Component(
    modules = [AppModule::class, DaoModule::class, DataSourceModule::class, MapperModule::class, PresenterModule::class,
        RepositoryModule::class, StorageRepositoryModule::class, UseCaseModule::class, ViewModelModule::class],
    dependencies = [NetworkComponent::class]
)
@ApplicationScope
interface AppComponent {

    fun inject(app: MainActivity)
    fun inject(fragment: FeedFragment)
    fun inject(fragment: MovieDetailsFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: TvShowsFragment)
    fun inject(fragment: WatchlistFragment)
    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(
            networkComponent: NetworkComponent,
            @BindsInstance applicationContext: Context
        ): AppComponent
    }
}