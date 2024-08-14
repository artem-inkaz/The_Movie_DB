package ru.androidschool.intensiv.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthMovieApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzODQ3MDk1Y2FkODQ0OWVjMWI5Y2E2MjQwZmE5MTAyYyIsIm5iZiI6MTcyMzU1MTQ2NS4wMTAzMDcsInN1YiI6IjVmZjE5NzRmMGM0YzE2MDAzZmE0YzExZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6Vc9NioS7qOraV5PJDkcAOgAKqNZyHsZzKaDsh85kUk")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}