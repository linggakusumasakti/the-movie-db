package com.data.source.local

import androidx.paging.DataSource
import com.data.source.local.entity.MovieEntity
import com.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) = movieDao.insertMovie(movie)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateFavoriteMovie(movie)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovie()

}