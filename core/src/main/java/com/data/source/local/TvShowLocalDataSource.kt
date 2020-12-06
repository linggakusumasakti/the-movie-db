package com.data.source.local

import androidx.paging.DataSource
import com.data.source.local.entity.TvShowEntity
import com.data.source.local.entity.TvShowFavoriteEntity
import com.data.source.local.room.TvShowDao
import javax.inject.Inject

class TvShowLocalDataSource @Inject constructor(private val tvShowDao: TvShowDao) {

    fun getAllTvShow(type: String) = tvShowDao.getAllTvShow(type)

    suspend fun insertTvShow(tvShow: List<TvShowEntity>) = tvShowDao.insertTvShow(tvShow)

    fun setFavoriteTvShow(tvShowEntity: TvShowFavoriteEntity, state: Boolean) {
        tvShowEntity.isFavorite = state
        tvShowDao.updateFavoriteTvShow(tvShowEntity)
    }

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowFavoriteEntity> =
        tvShowDao.getFavoriteTvShow()

    fun getTvShowById(id: Int) = tvShowDao.getTvShowById(id)
}