package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Observable
import ru.androidschool.intensiv.data.vo.TvShowsLocal
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import ru.androidschool.intensiv.extensions.applySchedulers
import javax.inject.Inject

class TvShowsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    operator fun invoke(): Observable<List<TvShowsLocal>> =
        repository.getAllTvShows().applySchedulers()
}