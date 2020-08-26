package com.lingga.themoviedb.core.data.source.local

import com.lingga.themoviedb.core.data.source.local.room.MovieDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
}