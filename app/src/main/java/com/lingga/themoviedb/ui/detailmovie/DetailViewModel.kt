package com.lingga.themoviedb.ui.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lingga.themoviedb.core.domain.usecase.movie.MovieInteractor
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val interactor: MovieInteractor) : ViewModel() {

    fun detail(id: Int) = interactor.getMovie(id).asLiveData()
}