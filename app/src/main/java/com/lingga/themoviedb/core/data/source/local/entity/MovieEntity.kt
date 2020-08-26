package com.lingga.themoviedb.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    var id: String,
    var title: String,
    var name: String,
    var overview: String,
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "poster_path") var posterPath: String,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String,
    @ColumnInfo(name = "vote_average") var voteAverage: String,
    @ColumnInfo(name = "release_date") var releaseDate: String,
    @ColumnInfo(name = "first_air_date") var firstAirDate: String
)