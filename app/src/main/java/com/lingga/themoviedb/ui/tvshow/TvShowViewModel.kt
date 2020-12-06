package com.lingga.themoviedb.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.domain.usecase.tvshow.TvShowUseCase

import javax.inject.Inject

class TvShowViewModel @Inject constructor(private val useCase: TvShowUseCase?) : ViewModel() {

    fun tvShow(type: String) = useCase?.getAllTvShow(type)?.asLiveData()
    fun airingTodayTvShow(type: String) = useCase?.getAiringTodayTvShow(type)?.asLiveData()
    fun latestTvShow(type: String) = useCase?.getLatestTvShow(type)?.asLiveData()
}