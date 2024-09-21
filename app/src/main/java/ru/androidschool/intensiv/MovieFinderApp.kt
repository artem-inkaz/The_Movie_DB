package ru.androidschool.intensiv

import android.app.Application
import android.content.Context
import ru.androidschool.intensiv.core.network.di.DaggerNetworkComponent
import ru.androidschool.intensiv.di.AppComponent
import ru.androidschool.intensiv.di.DaggerAppComponent
import timber.log.Timber

class MovieFinderApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        val networkComponent = DaggerNetworkComponent.create()
        appComponent = DaggerAppComponent.factory().create(networkComponent, applicationContext = this)
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
}

fun Context.appComponent(): AppComponent =
    (this.applicationContext as MovieFinderApp).appComponent
