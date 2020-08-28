package com.lingga.themoviedb.core.domain.model

import android.os.Parcelable
import com.lingga.themoviedb.utils.ext.parseDateWithFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val isFavorite: Boolean?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: String?,
    val firstAirDate: String?
) : Parcelable{
    fun getReleaseDateFormat(): String = firstAirDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}