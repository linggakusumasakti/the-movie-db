package com.data.source.remote.response.tvshow

import com.data.source.remote.response.GenreResponse
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("genres") val genres: List<GenreResponse>? = emptyList(),
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: String?,
    @SerializedName("first_air_date") val firsAirDate: String?
)