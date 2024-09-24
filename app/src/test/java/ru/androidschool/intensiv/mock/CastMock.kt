package ru.androidschool.intensiv.mock

import ru.androidschool.intensiv.core.network.dto.moveidcredits.Cast
import ru.androidschool.intensiv.core.network.utils.movieParams
import ru.androidschool.intensiv.data.vo.Actor

val mockCast = Cast(
    adult = false,
    cast_id = 1,
    character = "character",
    credit_id = "credit_id",
    gender = 1,
    id = 1,
    known_for_department = "known_for_department",
    name = "name",
    order = 1,
    original_name = "original_name",
    popularity = 1.0,
    profile_path = "${movieParams.baseImageUrl}7PSShz1Ht0qK2kqzC0jC31ktKPI.jpg"
)

val mockActor = Actor(
    id = 1,
    name = "name",
    profilePath = "${movieParams.baseImageUrl}7PSShz1Ht0qK2kqzC0jC31ktKPI.jpg"
)
