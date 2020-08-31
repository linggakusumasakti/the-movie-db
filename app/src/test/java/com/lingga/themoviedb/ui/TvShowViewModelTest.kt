package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowUseCase
import com.lingga.themoviedb.ui.tvshow.TvShowViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<TvShow>>>

    @Mock
    private lateinit var list: List<TvShow>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun getTvShow() {
        val tvShowDummy = Resource.Success(list)
        val tvShow = MutableLiveData<Resource<List<TvShow>>>()
        tvShow.value = tvShowDummy

        Mockito.`when`(useCase.getAllTvShow()).thenReturn(tvShow.asFlow())
        verify(useCase).getAllTvShow()

        viewModel.tvShow?.observeForever(observer)
    }
}