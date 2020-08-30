package com.lingga.themoviedb.core.data.source.local.room

import androidx.room.*
import com.lingga.themoviedb.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

  @Query("select * from tv_show")
  fun getAllTvShow(): Flow<List<TvShowEntity>>

  @Query("select * from tv_show where isFavorite = 1")
  fun getFavoriteTvShow(): Flow<List<TvShowEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTvShow(tvShow: List<TvShowEntity>)

  @Update
  fun updateFavoriteTvShow(tvShow: TvShowEntity)

  @Query("select * from tv_show where id = :id")
  fun getTvShow(id: Int): Flow<TvShowEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertDetailTvShow(tvShow: TvShowEntity)
}