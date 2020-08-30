package com.lingga.themoviedb.core.data.source.local

import com.lingga.themoviedb.core.data.source.local.entity.TvShowEntity
import com.lingga.themoviedb.core.data.source.local.room.TvShowDao
import javax.inject.Inject

class TvShowLocalDataSource @Inject constructor(private val tvShowDao: TvShowDao) {

  fun getAllTvShow() = tvShowDao.getAllTvShow()

  suspend fun insertTvShow(tvShow: List<TvShowEntity>) = tvShowDao.insertTvShow(tvShow)

  fun getTvShow(id: Int) = tvShowDao.getTvShow(id)

  suspend fun insertDetailTvShow(tvShow: TvShowEntity) = tvShowDao.insertDetailTvShow(tvShow)
}