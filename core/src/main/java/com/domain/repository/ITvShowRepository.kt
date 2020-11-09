package com.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.data.Resource
import com.domain.model.Credit
import com.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    suspend fun getTvShow(id: Int): Flow<TvShow>
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
    fun getFavoriteTvShow(): LiveData<PagedList<TvShow>>
    suspend fun getSearchTvShow(query: String): Flow<List<TvShow>>
    suspend fun getCreditTvShow(id: Int): Flow<List<Credit>>
    suspend fun getTvShowById(id: Int): Flow<TvShow>
}