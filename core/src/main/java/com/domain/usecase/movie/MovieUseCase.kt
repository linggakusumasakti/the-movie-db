package com.domain.usecase.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.data.Resource
import com.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Movie>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getFavoriteMovie(): LiveData<PagedList<Movie>>
}