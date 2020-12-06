package com.domain.model

import android.os.Parcelable
import com.data.source.remote.response.GenreResponse
import com.utils.ext.parseDateWithFormat
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TvShow(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val isFavorite: Boolean?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: String?,
    val firstAirDate: String?,
    val type: String? = "",
    val genres: @RawValue List<GenreResponse>? = emptyList()
) : Parcelable {
    fun getReleaseDateFormat(): String = firstAirDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}