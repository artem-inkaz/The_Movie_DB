package ru.androidschool.intensiv.data.datasource.api.moviedetail

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.network.MovieApiInterface

class MovieDetailNetworkDataSource(private val apiClient: MovieApiInterface) :
    MovieDetailDataSource {

    override fun getMovieDetails(id: MovieId): Single<MoveIdResponse> {
        return apiClient.getMovieDetails(id)
    }
}