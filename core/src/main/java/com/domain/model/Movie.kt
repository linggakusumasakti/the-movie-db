package com.domain.model

import android.os.Parcelable
import com.data.source.remote.response.GenreResponse
import com.utils.ext.parseDateWithFormat
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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
    val popularity: Float?,
    val genres: @RawValue List<GenreResponse>? = emptyList(),
    val type: String? = ""
) : Parcelable {

    fun getReleaseDateFormat(): String = releaseDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}