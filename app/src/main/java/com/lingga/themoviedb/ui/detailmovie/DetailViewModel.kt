package com.lingga.themoviedb.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.usecase.movie.MovieInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val interactor: MovieInteractor) : ViewModel() {

    private val _detail = MutableLiveData<Movie>()
    val detail: LiveData<Movie> get() = _detail

    fun getDetail(id: Int) {
        viewModelScope.launch {
            interactor.getMovie(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }
}