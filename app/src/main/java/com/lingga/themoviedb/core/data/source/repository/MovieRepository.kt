package com.lingga.themoviedb.core.data.source.repository

import com.lingga.themoviedb.core.data.NetworkBoundResource
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.data.source.local.MovieLocalDataSource
import com.lingga.themoviedb.core.data.source.remote.MovieRemoteDataSource
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
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.fetchMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.responseToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)

            }
        }.asFlow()

    override fun getMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovie(id).map {
                    DataMapper.entityToDomainMovie(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.fetchDetailMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.responseToEntityMovie(data)
                localDataSource.insertDetailMovie(movie)
            }
        }.asFlow()
}