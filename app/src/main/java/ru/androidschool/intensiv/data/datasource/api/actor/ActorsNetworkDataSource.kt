package ru.androidschool.intensiv.data.datasource.api.actor

import io.reactivex.Single
import ru.androidschool.intensiv.core.network.MovieApiInterface
import ru.androidschool.intensiv.core.network.dto.moveid.MovieId
import ru.androidschool.intensiv.core.network.dto.moveidcredits.MoveIdCreditsResponse

import javax.inject.Inject

class ActorsNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    ActorsDataSource {
    override fun getActorsByMovieId(id: MovieId): Single<MoveIdCreditsResponse> {
        return apiClient.getMovieIdCredits(id)
    }
}