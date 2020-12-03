package com.data.source.local

import androidx.paging.DataSource
import com.data.source.local.entity.MovieEntity
import com.data.source.local.entity.MovieFavoriteEntity
import com.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(type:String): Flow<List<MovieEntity>> = movieDao.getAllMovie(type)

    suspend fun insertMovie(movie: List<MovieEntity>) = movieDao.insertMovie(movie)

    fun setFavoriteMovie(movie: MovieFavoriteEntity, state: Boolean) {
        movie.isFavorite = state
        movieDao.updateFavoriteMovie(movie)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieFavoriteEntity> =
        movieDao.getFavoriteMovie()

    fun getMovieById(id: Int) = movieDao.getMovieById(id)
}