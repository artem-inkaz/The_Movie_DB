package ru.androidschool.intensiv.core.network.di

import dagger.Component
import ru.androidschool.intensiv.core.network.MovieApiInterface
import javax.inject.Scope

@Scope
@Retention
annotation class NetworkScope

@Component(modules = [NetworkModule::class])
@NetworkScope
interface NetworkComponent {
    val movieApiInterface: MovieApiInterface
}

