package com.data.source.remote.response

data class BaseMovieResponse<T>(
  val results: List<T>?
)