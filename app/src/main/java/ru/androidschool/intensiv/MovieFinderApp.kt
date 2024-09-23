package ru.androidschool.intensiv

import android.app.Application
import android.content.Context
import ru.androidschool.intensiv.core.network.di.DaggerNetworkComponent
import ru.androidschool.intensiv.core.storage.di.DaggerStorageComponent
import ru.androidschool.intensiv.di.AppComponent
import ru.androidschool.intensiv.di.DaggerAppComponent
import timber.log.Timber

class MovieFinderApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        val networkComponent = DaggerNetworkComponent.create()
        val storageComponent = DaggerStorageComponent.factory().create(this)
        appComponent = DaggerAppComponent.builder()
            .networkComponent(networkComponent)
            .storageComponent(storageComponent)
            .build()
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set
    }
}

fun Context.appComponent(): AppComponent =
    (this.applicationContext as MovieFinderApp).appComponent
