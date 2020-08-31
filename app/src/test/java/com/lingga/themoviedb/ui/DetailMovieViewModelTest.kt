package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import com.lingga.themoviedb.ui.detailmovie.DetailViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val id = 718444

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Movie>

    @Mock
    private lateinit var data: Movie

    @Before
    fun setUp() {
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun getDetail() {
        val movieDummy = data
        val movie = MutableLiveData<Movie>()
        movie.value = movieDummy
        suspend {
            Mockito.`when`(useCase.getMovie(id))
            verify(viewModel.detail).observeForever(observer)
            verify(observer).onChanged(movieDummy)
        }
    }
}