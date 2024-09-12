package ru.androidschool.intensiv.data.datasource.api.moviedetail

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.moveid.MoveIdResponse
import ru.androidschool.intensiv.data.dto.moveid.MovieId

interface MovieDetailDataSource {
    fun getMovieDetails(id: MovieId): Single<MoveIdResponse>
}