package com.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_favorite")
data class TvFavoriteEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    var name: String?,
    var isFavorite: Boolean? = false,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?
)