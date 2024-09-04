package ru.androidschool.intensiv

import android.app.Application
import android.content.Context
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        // для БД
        appContext = this
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

       lateinit var appContext: Context
        fun context(): Context = appContext ?: throw IllegalStateException()
    }
}
