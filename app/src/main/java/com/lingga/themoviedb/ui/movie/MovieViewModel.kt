package com.lingga.themoviedb.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase?) : ViewModel() {
    fun movie(type: String) = movieUseCase?.getAllMovie(type)?.asLiveData()
    fun nowPlayingMovie(type: String) = movieUseCase?.getNowPlayingMovie(type)?.asLiveData()
    fun upComingMovie(type: String) = movieUseCase?.getUpComingMovie(type)?.asLiveData()
}