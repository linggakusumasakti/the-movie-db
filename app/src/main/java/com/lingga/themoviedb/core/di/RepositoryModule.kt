package com.lingga.themoviedb.core.di

import com.lingga.themoviedb.core.data.source.repository.MovieRepository
import com.lingga.themoviedb.core.data.source.repository.TvShowRepository
import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import com.lingga.themoviedb.core.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Binds
  abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

  @Binds
  abstract fun provideTvShowRepository(tvShowRepository: TvShowRepository): ITvShowRepository
}