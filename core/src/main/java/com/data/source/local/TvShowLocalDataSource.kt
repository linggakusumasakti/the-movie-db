package com.data.source.local

import androidx.paging.DataSource
import com.data.source.local.entity.TvShowEntity
import com.data.source.local.room.TvShowDao
import javax.inject.Inject

class TvShowLocalDataSource @Inject constructor(private val tvShowDao: TvShowDao) {

    fun getAllTvShow() = tvShowDao.getAllTvShow()

    suspend fun insertTvShow(tvShow: List<TvShowEntity>) = tvShowDao.insertTvShow(tvShow)

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, state: Boolean) {
        tvShowEntity.isFavorite = state
        tvShowDao.updateFavoriteTvShow(tvShowEntity)
    }

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getFavoriteTvShow()
}