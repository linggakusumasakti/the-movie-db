package com.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_favorite")
data class TvShowFavoriteEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    var name: String?,
    var overview: String?,
    var isFavorite: Boolean? = false,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?,
    @ColumnInfo(name = "vote_average") var voteAverage: String?,
    @ColumnInfo(name = "first_air_date") var firstAirDate: String?
)