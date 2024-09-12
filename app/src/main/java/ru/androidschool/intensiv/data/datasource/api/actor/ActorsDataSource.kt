package ru.androidschool.intensiv.data.datasource.api.actor

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MovieId
import ru.androidschool.intensiv.data.dto.moveidcredits.MoveIdCreditsResponse

interface ActorsDataSource {
    fun getActorsByMovieId(id: MovieId): Single<MoveIdCreditsResponse>
}