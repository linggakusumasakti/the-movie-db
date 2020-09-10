package com.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseMovieResponse<T>(
  @SerializedName("results") val results: List<T>?
)