package com.lingga.themoviedb.favorites

import androidx.lifecycle.ViewModel
import com.domain.usecase.movie.MovieUseCase
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(useCase: MovieUseCase?) : ViewModel() {

    val favoriteMovie = useCase?.getFavoriteMovie()

}