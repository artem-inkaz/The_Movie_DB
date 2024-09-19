package ru.androidschool.intensiv.data.datasource.api.actor

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.moveidcredits.MoveIdCreditsResponse
import ru.androidschool.intensiv.data.network.MovieApiInterface
import javax.inject.Inject

class ActorsNetworkDataSource @Inject constructor(private val apiClient: MovieApiInterface) :
    ActorsDataSource {
    override fun getActorsByMovieId(id: MovieId): Single<MoveIdCreditsResponse> {
        return apiClient.getMovieIdCredits(id)
    }
}