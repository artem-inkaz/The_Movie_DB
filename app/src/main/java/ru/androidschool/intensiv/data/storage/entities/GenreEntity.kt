package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GenreEntity.TABLE_NAME)
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = GENRE_ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String
) {
    companion object {
        const val TABLE_NAME = "genres"
        const val GENRE_ID = "genre_id"
        const val NAME = "name"
    }
}
