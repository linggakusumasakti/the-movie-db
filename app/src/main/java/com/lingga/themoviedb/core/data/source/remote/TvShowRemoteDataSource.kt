package com.lingga.themoviedb.core.data.source.remote

import android.util.Log
import com.lingga.themoviedb.core.data.source.remote.network.ApiResponse
import com.lingga.themoviedb.core.data.source.remote.network.MovieApiService
import com.lingga.themoviedb.core.data.source.remote.response.tvshow.TvShowResponse
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
}