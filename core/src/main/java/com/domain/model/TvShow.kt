package com.domain.model

import android.os.Parcelable
import com.data.source.remote.response.GenreResponse
import com.google.firebase.firestore.PropertyName
import com.utils.ext.parseDateWithFormat
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TvShow(
    @PropertyName("id") val id: Int? = 0,
    @PropertyName("name") val name: String? = "",
    @PropertyName("overview") val overview: String? = "",
    @PropertyName("isFavorite") val isFavorite: Boolean? = false,
    @PropertyName("poster") val posterPath: String? = "",
    @PropertyName("backdrop") val backdropPath: String? = "",
    @PropertyName("voteAverage") val voteAverage: String? = "",
    @PropertyName("firstAirDate") val firstAirDate: String? = "",
    val type: String? = "",
    val genres: @RawValue List<GenreResponse>? = emptyList()
) : Parcelable {
    fun getReleaseDateFormat(): String = firstAirDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}