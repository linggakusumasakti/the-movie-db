package com.lingga.themoviedb.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowUseCase
import com.lingga.themoviedb.ui.favorite.FavoriteTvShowViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class FavoriteTvShowViewModelTest {
    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: TvShowUseCase

    @Mock
    private lateinit var observer: Observer<PagedList<TvShow>>

    @Mock
    private lateinit var list: PagedList<TvShow>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(useCase)
    }

    @Test
    fun getTvShow() {
        val tvShowDummy = list
        val tvShow = MutableLiveData<PagedList<TvShow>>()
        tvShow.value = tvShowDummy

        Mockito.`when`(useCase.getFavoriteTvShow()).thenReturn(tvShow)
        verify(useCase).getFavoriteTvShow()

        viewModel.favoriteTvShow?.observeForever(observer)
    }
}