package com.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.data.source.local.entity.TvShowEntity
import com.data.source.local.entity.TvShowFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("select * from tv_show where type = :type")
    fun getAllTvShow(type:String): Flow<List<TvShowEntity>>

    @Query("select * from tv_show_favorite where isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoriteTvShow(tvShow: TvShowFavoriteEntity)

    @Query("select * from tv_show_favorite where id =:id")
    fun getTvShowById(id: Int): Flow<TvShowFavoriteEntity>

}