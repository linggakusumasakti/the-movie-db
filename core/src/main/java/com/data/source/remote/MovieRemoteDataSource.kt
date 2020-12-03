package com.data.source.remote

import android.util.Log
import com.data.source.remote.network.ApiResponse
import com.data.source.remote.network.MovieApiService
import com.data.source.remote.response.CreditResponse
import com.data.source.remote.response.movie.MovieResponse
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

    suspend fun fetchNowPlayingMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = movieApiService.getNowPlayingMovie()
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
            try {
                val data = movieApiService.getDetailMovie(movieId = id)
                emit(data)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchSearchMovie(query: String): Flow<List<MovieResponse>> {
        return flow {
            try {
                val response = movieApiService.getSearchMovie(query = query)
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(response.results)
                }
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchCreditMovie(id: Int): Flow<List<CreditResponse>> {
        return flow {
            try {
                val response = movieApiService.getCredits(movieId = id)
                val data = response.cast
                if (data?.isNotEmpty() ?: return@flow) emit(data)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}