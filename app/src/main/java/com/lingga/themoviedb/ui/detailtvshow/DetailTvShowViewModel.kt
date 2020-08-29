package com.lingga.themoviedb.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val interactor: TvShowInteractor) :
    ViewModel() {

    private val _detail = MutableLiveData<TvShow>()
    val detail: LiveData<TvShow> get() = _detail


    fun getDetail(id: Int) {
        viewModelScope.launch {
            interactor.getTvShow(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }
}