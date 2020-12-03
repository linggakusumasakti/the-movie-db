package com.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.data.Resource
import com.domain.model.Credit
import com.domain.model.Movie
import com.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {

    override fun getAllMovie(type: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovie(type)

    override suspend fun getMovie(id: Int): Flow<Movie> = movieRepository.getMovie(id)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> = movieRepository.getFavoriteMovie()

    override suspend fun getSearchMovie(query: String): Flow<List<Movie>> =
        movieRepository.getSearchMovie(query)

    override suspend fun getCreditMovie(id: Int): Flow<List<Credit>> =
        movieRepository.getCreditMovie(id)

    override suspend fun getMovieById(id: Int): Flow<Movie> = movieRepository.getMovieById(id)

    override fun getNowPlayingMovie(type: String): Flow<Resource<List<Movie>>> =
        movieRepository.getNowPlayingMovie(type)


}