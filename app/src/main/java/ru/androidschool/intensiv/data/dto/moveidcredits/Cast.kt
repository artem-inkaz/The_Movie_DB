package ru.androidschool.intensiv.data.dto.moveidcredits

import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.BuildConfig

data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
//    val profile_path: String? = null
){
    @SerializedName("profile_path")
    val profilePath: String? = null
        get() = "${BuildConfig.BASE_IMAGE_URL}${field}"
}