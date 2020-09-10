package com.data.source.remote.response.movie

import com.data.source.remote.response.GenreResponse
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: String?,
    @SerializedName("release_date") val releaseDate: String?
)