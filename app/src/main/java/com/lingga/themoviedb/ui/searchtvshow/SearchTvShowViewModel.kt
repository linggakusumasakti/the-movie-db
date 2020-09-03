package com.lingga.themoviedb.ui.searchtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.domain.model.TvShow
import com.domain.usecase.tvshow.TvShowUseCase
import com.lingga.themoviedb.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchTvShowViewModel @Inject constructor(private val useCase: TvShowUseCase) :
    BaseViewModel() {

    private val _search = MutableLiveData<List<TvShow>>()
    val search: LiveData<List<TvShow>> get() = _search

    val error = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getSearch(query: String) {
        viewModelScope.launch {
            useCase.getSearchTvShow(query).onStart {
                setLoading()
            }
                .catch {
                    error.postValue(this.toString())
                    finishLoading()
                }
                .collect {
                    _search.postValue(it)
                    finishLoading()
                }

        }
    }
}