package com.lingga.themoviedb.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName
import com.lingga.themoviedb.core.data.source.remote.response.GenreResponse

data class TvShowResponse(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val genres: List<GenreResponse>? = emptyList(),
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: String?,
    @SerializedName("first_air_date") val firsAirDate: String?
)