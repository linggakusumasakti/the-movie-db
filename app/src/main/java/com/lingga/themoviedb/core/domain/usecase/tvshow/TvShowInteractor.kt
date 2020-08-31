package com.lingga.themoviedb.core.domain.usecase.tvshow

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(private val repository: ITvShowRepository) :
    TvShowUseCase {

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> = repository.getAllTvShow()

    override suspend fun getTvShow(id: Int): Flow<TvShow> = repository.getTvShow(id)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        repository.setFavoriteTvShow(tvShow, state)

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShow>> = repository.getFavoriteTvShow()
}