package com.lingga.themoviedb.core.di

import com.lingga.themoviedb.core.data.source.MovieRepository
import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository
}