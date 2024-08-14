package ru.androidschool.intensiv.base

sealed interface MapperDomain {
    interface Base<In, Out> {
        fun toView(data: Out): In
        fun toDomain(data: In): Out
    }

    interface ReadOnly<In, Out> {
        fun toView(data: In): Out
    }
}