package com.data.source.remote

import android.util.Log
import com.data.source.remote.network.ApiResponse
import com.data.source.remote.network.MovieApiService
import com.data.source.remote.response.CreditResponse
import com.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRemoteDataSource @Inject constructor(private val movieApiService: MovieApiService) {

    suspend fun fetchTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = movieApiService.getTvShow()
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchAiringTodayTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = movieApiService.getAiringTodayTvShow()
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchLatestTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = movieApiService.getTopRatedTvShow()
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchDetailTvShow(id: Int): Flow<TvShowResponse> {
        return flow {
            try {
                val data = movieApiService.getDetailTvShow(tvId = id)
                emit(data)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchSearchTvShow(query: String): Flow<List<TvShowResponse>> {
        return flow {
            try {
                val response = movieApiService.getSearchTvShow(query = query)
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(response.results)
                }
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchCreditTvShow(id: Int): Flow<List<CreditResponse>> {
        return flow {
            try {
                val response = movieApiService.getCreditsTvShow(tvId = id)
                val data = response.cast
                if (data?.isNotEmpty() ?: return@flow) emit(data)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}