package com.lingga.themoviedb.core.data.source.remote

import android.util.Log
import com.lingga.themoviedb.core.data.source.remote.network.ApiResponse
import com.lingga.themoviedb.core.data.source.remote.network.MovieApiService
import com.lingga.themoviedb.core.data.source.remote.response.tvshow.TvShowResponse
import com.lingga.themoviedb.utils.EspressoIdlingResource
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
                EspressoIdlingResource.increment()
                val response = movieApiService.getTvShow()
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(data))
                    EspressoIdlingResource.decrement()
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
                EspressoIdlingResource.increment()
                val data = movieApiService.getDetailTvShow(tvId = id)
                emit(data)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}