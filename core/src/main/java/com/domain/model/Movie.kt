package com.domain.model

import android.os.Parcelable
import com.data.source.remote.response.GenreResponse
import com.google.firebase.firestore.PropertyName
import com.utils.ext.parseDateWithFormat
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movie(
    @PropertyName("id") val id: Int? = 0,
    @PropertyName("title") val title: String? = "",
    @PropertyName("overview") val overview: String? = "",
    @PropertyName("isFavorite") val isFavorite: Boolean? = false,
    @PropertyName("poster") val posterPath: String? = "",
    @PropertyName("backdrop") val backdropPath: String? = "",
    @PropertyName("voteAverage") val voteAverage: String? = "",
    @PropertyName("releaseDate") val releaseDate: String? = "",
    @PropertyName("popularity") val popularity: Float? = 0f,
    val genres: @RawValue List<GenreResponse>? = emptyList(),
    val type: String? = ""
) : Parcelable {

    fun getReleaseDateFormat(): String = releaseDate?.parseDateWithFormat("MM/dd/yyyy") ?: ""
    fun getRatingBar(): Float = if (voteAverage != null) voteAverage.toFloat() / 2 else 0f
}