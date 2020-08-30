package com.lingga.themoviedb.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(useCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = useCase.getFavoriteMovie().asLiveData()

}