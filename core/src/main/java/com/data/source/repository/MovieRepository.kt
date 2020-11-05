package com.data.source.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.data.NetworkBoundResource
import com.data.Resource
import com.data.source.local.MovieLocalDataSource
import com.data.source.remote.MovieRemoteDataSource
import com.data.source.remote.network.ApiResponse
import com.data.source.remote.response.movie.MovieResponse
import com.domain.model.Credit
import com.domain.model.Movie
import com.domain.repository.IMovieRepository
import com.utils.AppExecutors
import com.utils.DataMapper
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

            override fun shouldFetch(data: List<Movie>?): Boolean = true

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
        val movieEntity = DataMapper.domainToEntityMovieFavorite(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> {
        val data = localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntityToDomainMovie(it)
        }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(data, config).build()
    }

    override suspend fun getSearchMovie(query: String): Flow<List<Movie>> =
        remoteDataSource.fetchSearchMovie(query).map {
            DataMapper.responseToDomainMovies(it)
        }

    override suspend fun getCreditMovie(id: Int): Flow<List<Credit>> =
        remoteDataSource.fetchCreditMovie(id).map {
            DataMapper.responseToDomainCreditMovie(it)
        }

    override suspend fun getMovieById(id: Int): Flow<Movie> =
        localDataSource.getMovieById(id).map {
            DataMapper.mapEntityToDomainMovie(it)
        }
}