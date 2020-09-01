package com.lingga.themoviedb.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.usecase.tvshow.TvShowUseCase

import javax.inject.Inject

class TvShowViewModel @Inject constructor(useCase: TvShowUseCase?) : ViewModel() {

    val tvShow = useCase?.getAllTvShow()?.asLiveData()
}