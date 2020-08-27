package com.lingga.themoviedb.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowUseCase
import javax.inject.Inject

class TvShowViewModel @Inject constructor(useCase: TvShowUseCase) : ViewModel() {

    val tvShow = useCase.getAllTvShow().asLiveData()
}