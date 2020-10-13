package com.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.source.local.entity.MovieEntity
import com.data.source.local.entity.MovieFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("select * from movie_favorite where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoriteMovie(movie: MovieFavoriteEntity)

}