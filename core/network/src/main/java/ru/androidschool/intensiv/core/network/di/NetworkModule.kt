package ru.androidschool.intensiv.core.network.di
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.core.network.AuthMovieApiInterceptor
import ru.androidschool.intensiv.core.network.BuildConfig
import ru.androidschool.intensiv.core.network.CustomHttpLogging
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.utils.movieParams

@Module
class NetworkModule {

    @Provides
    @NetworkScope
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
    @NetworkScope
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

    @Provides
    @NetworkScope
    fun provideMovieService(client: OkHttpClient): MovieApiInterface {
        return Retrofit.Builder()
            .baseUrl(movieParams.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieApiInterface::class.java)
    }

    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"
}