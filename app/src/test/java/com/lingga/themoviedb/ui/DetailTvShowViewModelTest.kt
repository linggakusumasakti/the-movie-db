package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowUseCase
import com.lingga.themoviedb.ui.detailtvshow.DetailTvShowViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val id = 718444

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Mock
    private lateinit var observer: Observer<TvShow>

    @Mock
    private lateinit var data: TvShow

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(useCase)
    }

    @Test
    fun getDetail() {
        val tvDummy = data
        val movie = MutableLiveData<TvShow>()
        movie.value = tvDummy
        suspend {
            Mockito.`when`(useCase.getTvShow(id))
            verify(viewModel.detail).observeForever(observer)
            verify(observer).onChanged(tvDummy)
        }
    }
}