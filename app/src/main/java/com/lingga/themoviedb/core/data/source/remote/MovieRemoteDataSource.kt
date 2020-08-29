package com.lingga.themoviedb.core.data.source.remote

import android.util.Log
import com.lingga.themoviedb.core.data.source.remote.network.ApiResponse
import com.lingga.themoviedb.core.data.source.remote.network.MovieApiService
import com.lingga.themoviedb.core.data.source.remote.response.movie.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val movieApiService: MovieApiService) {

    suspend fun fetchMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieApiService.getMovie()
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchDetailMovie(id: Int): Flow<MovieResponse> {
        return flow {
            val data = movieApiService.getDetailMovie(movieId = id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}