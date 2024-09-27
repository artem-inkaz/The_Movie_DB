package ru.androidschool.intensiv.mock

import ru.androidschool.intensiv.data.vo.Genre

val mockGenreVo = Genre(
    id = 1,
    name = "name"
)

val mockGenreDto = ru.androidschool.intensiv.core.network.dto.moveid.Genre(
    id = 1,
    name = "name"
)