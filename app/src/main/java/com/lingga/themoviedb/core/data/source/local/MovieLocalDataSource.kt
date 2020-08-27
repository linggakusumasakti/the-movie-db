package com.lingga.themoviedb.core.data.source.local

import com.lingga.themoviedb.core.data.source.local.entity.MovieEntity
import com.lingga.themoviedb.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) = movieDao.insertMovie(movie)

    fun getMovie(id: Int): Flow<MovieEntity> = movieDao.getMovie(id)

    suspend fun insertDetailMovie(movie: MovieEntity) = movieDao.insertDetailMovie(movie)
}