package com.lingga.themoviedb.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(movieUseCase: MovieUseCase?) : ViewModel() {
  val movie = movieUseCase?.getAllMovie()?.asLiveData()
}