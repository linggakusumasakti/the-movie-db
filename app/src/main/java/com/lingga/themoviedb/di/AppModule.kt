package com.lingga.themoviedb.di

import com.lingga.themoviedb.core.domain.usecase.MovieInteractor
import com.lingga.themoviedb.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}