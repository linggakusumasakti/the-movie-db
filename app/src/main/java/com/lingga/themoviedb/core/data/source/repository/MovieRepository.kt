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

    override suspend fun getMovie(id: Int): Flow<Movie> =
        remoteDataSource.fetchDetailMovie(id).map {
            DataMapper.responseToDomainMovie(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.domainToEntityMovie(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomainMovie(it)
        }
    }
}