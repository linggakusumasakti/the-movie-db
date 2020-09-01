package com.lingga.themoviedb.di

import com.domain.usecase.movie.MovieInteractor
import com.domain.usecase.movie.MovieUseCase
import com.domain.usecase.tvshow.TvShowInteractor
import com.domain.usecase.tvshow.TvShowUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvShowUseCase(interactor: TvShowInteractor): TvShowUseCase
}