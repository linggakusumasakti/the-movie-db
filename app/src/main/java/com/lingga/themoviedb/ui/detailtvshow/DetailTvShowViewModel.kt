package com.lingga.themoviedb.ui.detailtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.Credit
import com.domain.model.TvShow
import com.domain.usecase.tvshow.TvShowUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTvShowViewModel @Inject constructor(private val useCase: TvShowUseCase) :
    ViewModel() {

    private val _detail = MutableLiveData<TvShow>()
    val detail: LiveData<TvShow> get() = _detail

    private val _credit = MutableLiveData<List<Credit>>()
    val credit: LiveData<List<Credit>> get() = _credit

    fun getDetail(id: Int) {
        viewModelScope.launch {
            useCase.getTvShow(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }

    fun getCreditTvShow(id: Int) {
        viewModelScope.launch {
            useCase.getCreditTvShow(id)
                .collect {
                    _credit.postValue(it)
                }
        }
    }

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) = useCase.setFavoriteTvShow(tvShow, state)
}