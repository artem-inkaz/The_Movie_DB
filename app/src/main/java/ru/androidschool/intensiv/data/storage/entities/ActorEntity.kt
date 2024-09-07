package ru.androidschool.intensiv.data.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ActorEntity.TABLE_NAME)
data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = ACTOR_ID)
    val id: Int,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = PROFILE_PATH)
    val profilePath: String
) {
    companion object {
        const val TABLE_NAME = "actors"
        const val ACTOR_ID = "actor_id"
        const val NAME = "name"
        const val PROFILE_PATH = "profile_path"
    }
}
