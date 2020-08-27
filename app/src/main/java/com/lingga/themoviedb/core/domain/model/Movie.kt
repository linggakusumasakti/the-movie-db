package com.lingga.themoviedb.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val isFavorite: Boolean?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: String?,
    val releaseDate: String?
) : Parcelable