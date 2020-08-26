package com.lingga.themoviedb.core.data.source.remote.network

import com.lingga.themoviedb.BuildConfig
import com.lingga.themoviedb.core.data.source.remote.response.movie.BaseMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMovie(@Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY): BaseMovieResponse

    @GET("tv/popular")
    suspend fun getTvShow(@Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY): BaseMovieResponse

}