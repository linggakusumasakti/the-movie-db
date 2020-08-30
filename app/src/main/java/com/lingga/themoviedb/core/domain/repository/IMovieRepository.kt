package com.lingga.themoviedb.core.domain.repository

import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Movie>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun getFavoriteMovie(): Flow<List<Movie>>
}
