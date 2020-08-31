package com.lingga.themoviedb.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(movieUseCase: MovieUseCase?) : ViewModel() {
  val movie = movieUseCase?.getAllMovie()?.asLiveData()
}