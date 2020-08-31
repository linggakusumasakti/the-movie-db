package com.lingga.themoviedb.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lingga.themoviedb.core.data.source.local.MovieLocalDataSource
import com.lingga.themoviedb.core.data.source.local.room.MovieDao
import com.lingga.themoviedb.core.data.source.local.room.MovieDatabase
import com.lingga.themoviedb.core.data.source.remote.MovieRemoteDataSource
import com.lingga.themoviedb.core.data.source.repository.MovieRepository
import com.lingga.themoviedb.core.utils.AppExecutors
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(MovieRemoteDataSource::class.java)
    private val dao = Mockito.mock(MovieDao::class.java)
    private val local = Mockito.mock(MovieLocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)


    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        val db = Mockito.mock(MovieDatabase::class.java)
        Mockito.`when`(db.movieDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = MovieRepository(remote, local, appExecutors)
    }

    @Test
    fun getAllMovies() {
        repository.getAllMovie()
        verify(dao, never()).getAllMovie()
    }

}