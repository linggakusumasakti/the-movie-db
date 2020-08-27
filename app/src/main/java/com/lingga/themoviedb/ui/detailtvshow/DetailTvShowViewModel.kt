package com.lingga.themoviedb.ui.detailtvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowInteractor
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val interactor: TvShowInteractor) :
    ViewModel() {

    fun detail(id: Int) = interactor.getTvShow(id).asLiveData()
}