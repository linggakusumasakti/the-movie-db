package com.lingga.themoviedb.ui.searchmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.domain.model.Movie
import com.domain.usecase.movie.MovieUseCase
import com.lingga.themoviedb.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(private val useCase: MovieUseCase) :
    BaseViewModel() {

    private val _search = MutableLiveData<List<Movie>>()
    val search: LiveData<List<Movie>> get() = _search

    val error = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getSearch(query: String) {
        viewModelScope.launch {
            useCase.getSearchMovie(query)
                .catch {
                    error.postValue(this.toString())
                    finishLoading()
                }
                .onStart {
                    setLoading()
                }
                .collect {
                    _search.postValue(it)
                    finishLoading()
                }

        }
    }
}