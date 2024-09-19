package ru.androidschool.intensiv.domain.usecase

import ru.androidschool.intensiv.data.vo.MovieLocal
import ru.androidschool.intensiv.domain.datasource.MovieStorageRepository
import javax.inject.Inject

class MovieStorageUseCase @Inject constructor(
    private val repository: MovieStorageRepository
) {
    operator fun invoke(data: List<MovieLocal>) {
        repository.insertAll(data)
    }
}