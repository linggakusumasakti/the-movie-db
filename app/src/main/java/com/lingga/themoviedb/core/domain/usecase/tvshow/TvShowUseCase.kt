package com.lingga.themoviedb.core.domain.usecase.tvshow

import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    suspend fun getTvShow(id: Int): Flow<TvShow>
}