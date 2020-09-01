package com.lingga.themoviedb.favorites

import androidx.lifecycle.ViewModel
import com.domain.usecase.tvshow.TvShowUseCase
import javax.inject.Inject

class FavoriteTvShowViewModel @Inject constructor(useCase: TvShowUseCase?) : ViewModel() {
    val favoriteTvShow = useCase?.getFavoriteTvShow()
}