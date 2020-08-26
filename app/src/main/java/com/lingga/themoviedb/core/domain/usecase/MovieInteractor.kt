package com.lingga.themoviedb.core.domain.usecase

import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(movieRepository: IMovieRepository) : MovieUseCase {
}