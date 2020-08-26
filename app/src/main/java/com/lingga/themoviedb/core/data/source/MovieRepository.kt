package com.lingga.themoviedb.core.data.source

import com.lingga.themoviedb.core.data.source.local.LocalDataSource
import com.lingga.themoviedb.core.data.source.remote.RemoteDataSource
import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import com.lingga.themoviedb.core.utils.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
}