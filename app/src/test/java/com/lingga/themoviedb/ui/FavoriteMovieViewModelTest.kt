package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import com.lingga.themoviedb.ui.favorite.FavoriteMovieViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @Mock
    private lateinit var list: PagedList<Movie>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(useCase)
    }

    @Test
    fun getMovie() {
        val movieDummy = list
        val movie = MutableLiveData<PagedList<Movie>>()
        movie.value = movieDummy

        Mockito.`when`(useCase.getFavoriteMovie()).thenReturn(movie)
        verify(useCase).getFavoriteMovie()

        viewModel.favoriteMovie?.observeForever(observer)
    }

}