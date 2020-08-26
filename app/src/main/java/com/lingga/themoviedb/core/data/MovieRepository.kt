package com.lingga.themoviedb.core.data

import com.lingga.themoviedb.core.data.source.local.LocalDataSource
import com.lingga.themoviedb.core.data.source.remote.RemoteDataSource
import com.lingga.themoviedb.core.data.source.remote.network.ApiResponse
import com.lingga.themoviedb.core.data.source.remote.response.movie.MovieResponse
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import com.lingga.themoviedb.core.utils.AppExecutors
import com.lingga.themoviedb.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.fetchMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.responseToEntities(data)
                localDataSource.insertMovie(movieList)

            }
        }.asFlow()
}