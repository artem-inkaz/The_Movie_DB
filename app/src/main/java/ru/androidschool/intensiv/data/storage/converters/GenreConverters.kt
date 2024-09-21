package ru.androidschool.intensiv.data.storage.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.androidschool.intensiv.core.network.dto.moveid.Genre

class GenreConverters {

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenre(name: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(name, listType)
    }
}