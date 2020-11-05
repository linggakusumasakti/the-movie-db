package com.lingga.themoviedb.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.Credit
import com.domain.model.Movie
import com.domain.usecase.movie.MovieUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _detail = MutableLiveData<Movie>()
    val detail: LiveData<Movie> get() = _detail

    private val _credit = MutableLiveData<List<Credit>>()
    val credit: LiveData<List<Credit>> get() = _credit

    private val _detailDb = MutableLiveData<Movie>()
    val detailDb: LiveData<Movie> get() = _detailDb

    fun getDetail(id: Int) {
        viewModelScope.launch {
            useCase.getMovie(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }


    fun getCredit(id: Int) {
        viewModelScope.launch {
            useCase.getCreditMovie(id).collect {
                _credit.postValue(it)
            }
        }
    }

    fun getDetailDb(id: Int) {
        viewModelScope.launch {
            useCase.getMovieById(id).collect {
                _detailDb.postValue(it)
            }
        }
    }

    fun setFavoriteMovie(movie: Movie, state: Boolean) = useCase.setFavoriteMovie(movie, state)


}