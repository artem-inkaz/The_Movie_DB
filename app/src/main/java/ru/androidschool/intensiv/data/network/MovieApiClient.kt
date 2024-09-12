package ru.androidschool.intensiv.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig

object MovieApiClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthMovieApiInterceptor())
        .addInterceptor(if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor(CustomHttpLogging()).apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        } else {
            HttpLoggingInterceptor(CustomHttpLogging()).apply {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        })
        .build()

    val apiClient: MovieApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}