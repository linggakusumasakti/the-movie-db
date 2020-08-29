package com.lingga.themoviedb.core.domain.usecase.tvshow

import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(private val repository: ITvShowRepository) :
    TvShowUseCase {

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> = repository.getAllTvShow()

    override suspend fun getTvShow(id: Int): Flow<TvShow> = repository.getTvShow(id)
}