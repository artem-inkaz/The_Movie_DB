package ru.androidschool.intensiv.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig.BASE_URL
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.network.MovieApiInterface
import ru.androidschool.intensiv.data.storage.database.MoviesDataBase
import javax.inject.Singleton

@Module
class AppModule {


//    @Provides
//    @Singleton
//    fun provideApp() = appContext

    @Provides
    @Singleton
    fun provideClient() =
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            })
            .addInterceptor(MovieApiClient.interceptor)
            .build()

    @Singleton
    @Provides
    fun provideGithubService(client: OkHttpClient): MovieApiInterface {
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
//
//
//    @Singleton
//    @Provides
//    fun provideLocalRepository(dao: MovieDao): MovieInterface {
//        return MovieLocalRepository(dao)
//    }
}