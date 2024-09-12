package ru.androidschool.intensiv.domain.usecase

import ru.androidschool.intensiv.data.vo.MovieGenre
import ru.androidschool.intensiv.domain.repository.MovieGenreRepository

class MovieGenreUseCase(
    private val repository: MovieGenreRepository
) {
    operator fun invoke(data: List<MovieGenre>) {
        repository.addAll(data)
    }
}