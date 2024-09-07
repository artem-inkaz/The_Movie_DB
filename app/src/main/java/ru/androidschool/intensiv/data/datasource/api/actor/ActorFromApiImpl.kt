package ru.androidschool.intensiv.data.datasource.api.actor

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.moveidcredits.MoveIdCreditsResponse
import ru.androidschool.intensiv.data.network.MovieApiClient

class ActorFromApiImpl : ActorFromApi {
    override fun getActorsFromMovie(id: MovieId): Single<MoveIdCreditsResponse> {
        return MovieApiClient.apiClient.getMovieIdCredits(id)
    }
}