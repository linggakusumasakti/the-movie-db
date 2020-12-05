package com.lingga.themoviedb.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class MoviePopularViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    fun movie(type: String) = useCase.getAllMovie(type).asLiveData()
}