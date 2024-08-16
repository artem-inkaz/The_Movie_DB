package ru.androidschool.intensiv.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.androidschool.intensiv.BuildConfig

class AuthMovieApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN_API}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}