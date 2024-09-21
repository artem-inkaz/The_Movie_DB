package ru.androidschool.intensiv.data.datasource.api.moviedetail

import io.reactivex.Single
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.core.network.dto.moveid.MovieId

import javax.inject.Inject

class MovieDetailNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    MovieDetailDataSource {

    override fun getMovieDetails(id: MovieId): Single<MoveIdResponse> {
        return apiClient.getMovieDetails(id)
    }
}