package com.lingga.themoviedb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lingga.themoviedb.core.data.source.local.TvShowLocalDataSource
import com.lingga.themoviedb.core.data.source.local.room.MovieDatabase
import com.lingga.themoviedb.core.data.source.local.room.TvShowDao
import com.lingga.themoviedb.core.data.source.remote.TvShowRemoteDataSource
import com.lingga.themoviedb.core.data.source.repository.TvShowRepository
import com.lingga.themoviedb.core.utils.AppExecutors
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class TvShowRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(TvShowRemoteDataSource::class.java)
    private val dao = Mockito.mock(TvShowDao::class.java)
    private val local = Mockito.mock(TvShowLocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)


    private lateinit var repository: TvShowRepository

    @Before
    fun setUp() {
        val db = Mockito.mock(MovieDatabase::class.java)
        Mockito.`when`(db.tvShowDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = TvShowRepository(remote, local, appExecutors)
    }

    @Test
    fun getAllMovies() {
        repository.getAllTvShow()
        verify(dao, never()).getAllTvShow()
    }
}