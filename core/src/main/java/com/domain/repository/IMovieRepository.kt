package com.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.data.Resource
import com.domain.model.Credit
import com.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Movie>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getFavoriteMovie(): LiveData<PagedList<Movie>>
    suspend fun getSearchMovie(query: String): Flow<List<Movie>>
    suspend fun getCreditMovie(id:Int): Flow<List<Credit>>
}
