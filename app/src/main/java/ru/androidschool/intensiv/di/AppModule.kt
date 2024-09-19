package ru.androidschool.intensiv.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.BuildConfig.BASE_URL
import ru.androidschool.intensiv.data.network.AuthMovieApiInterceptor
import ru.androidschool.intensiv.data.network.CustomHttpLogging
import ru.androidschool.intensiv.data.network.MovieApiInterface
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(CustomHttpLogging()).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            })
            .addInterceptor(AuthMovieApiInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideMovieService(client: OkHttpClient): MovieApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Context): MoviesDataBase {
        return MoviesDataBase.get(app)
    }
}