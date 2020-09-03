package com.data.source.remote.network

import com.data.source.remote.response.BaseMovieResponse
import com.data.source.remote.response.movie.MovieResponse
import com.data.source.remote.response.tvshow.TvShowResponse
import com.lingga.themoviedb.core.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getMovie(@Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY): BaseMovieResponse<MovieResponse>

    @GET("tv/popular")
    suspend fun getTvShow(@Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY): BaseMovieResponse<TvShowResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): MovieResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY
    ): TvShowResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") apiKey: String? = BuildConfig.TMDB_API_KEY,
        @Query("query") query: String?
    ): BaseMovieResponse<MovieResponse>
}