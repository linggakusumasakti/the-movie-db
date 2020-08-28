package com.lingga.themoviedb.core.domain.model

import android.os.Parcelable
import com.lingga.themoviedb.utils.ext.parseDateWithFormat
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
    val releaseDate: String?,
    val popularity: Float?
) : Parcelable {

    fun getReleaseDateFormat(): String = releaseDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}