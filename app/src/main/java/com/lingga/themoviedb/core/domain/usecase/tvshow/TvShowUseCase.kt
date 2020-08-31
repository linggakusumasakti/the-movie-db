package com.lingga.themoviedb.core.domain.usecase.tvshow

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    suspend fun getTvShow(id: Int): Flow<TvShow>
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
    fun getFavoriteTvShow(): LiveData<PagedList<TvShow>>
}