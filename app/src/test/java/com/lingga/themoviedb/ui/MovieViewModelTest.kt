package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import com.lingga.themoviedb.ui.movie.MovieViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var list: List<Movie>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun getMovie() {
        val movieDummy = Resource.Success(list)
        val movie = MutableLiveData<Resource<List<Movie>>>()
        movie.value = movieDummy

        Mockito.`when`(useCase.getAllMovie()).thenReturn(movie.asFlow())
        verify(useCase).getAllMovie()

        viewModel.movie?.observeForever(observer)
    }
}