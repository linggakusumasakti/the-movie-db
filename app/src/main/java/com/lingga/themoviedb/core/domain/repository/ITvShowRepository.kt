package com.lingga.themoviedb.core.domain.repository

import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
}