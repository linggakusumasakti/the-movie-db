package com.di

import com.data.source.repository.MovieRepository
import com.data.source.repository.TvShowRepository
import com.domain.repository.IMovieRepository
import com.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

  @Binds
  abstract fun provideTvShowRepository(tvShowRepository: TvShowRepository): ITvShowRepository
}