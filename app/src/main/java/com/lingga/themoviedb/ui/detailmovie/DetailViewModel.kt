package com.lingga.themoviedb.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _detail = MutableLiveData<Movie>()
    val detail: LiveData<Movie> get() = _detail

    fun getDetail(id: Int) {
        viewModelScope.launch {
            useCase.getMovie(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }

    fun setFavoriteMovie(movie: Movie, state: Boolean) = useCase.setFavoriteMovie(movie, state)

}