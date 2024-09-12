package ru.androidschool.intensiv.domain.repository

import io.reactivex.Observable
import ru.androidschool.intensiv.data.vo.Actor

interface ActorRepository {
    fun getAll(): Observable<List<Actor>>
}