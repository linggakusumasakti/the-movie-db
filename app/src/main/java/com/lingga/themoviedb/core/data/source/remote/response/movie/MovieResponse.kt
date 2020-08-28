package com.lingga.themoviedb.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val popularity: Float?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: String?,
    @SerializedName("release_date") val releaseDate: String?
)