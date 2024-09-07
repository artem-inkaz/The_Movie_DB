package ru.androidschool.intensiv.domain.repository

import io.reactivex.Observable
import ru.androidschool.intensiv.data.vo.Genre

interface GenreRepository {
    fun getAll(): Observable<List<Genre>>
}