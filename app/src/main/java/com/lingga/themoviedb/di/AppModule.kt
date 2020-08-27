package com.lingga.themoviedb.di

import com.lingga.themoviedb.core.domain.usecase.movie.MovieInteractor
import com.lingga.themoviedb.core.domain.usecase.movie.MovieUseCase
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowInteractor
import com.lingga.themoviedb.core.domain.usecase.tvshow.TvShowUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvShowUseCase(interactor: TvShowInteractor): TvShowUseCase
}