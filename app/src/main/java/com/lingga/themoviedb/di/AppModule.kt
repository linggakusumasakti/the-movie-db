package com.lingga.themoviedb.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.domain.usecase.movie.MovieInteractor
import com.domain.usecase.movie.MovieUseCase
import com.domain.usecase.tvshow.TvShowInteractor
import com.domain.usecase.tvshow.TvShowUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvShowUseCase(interactor: TvShowInteractor): TvShowUseCase

    @Binds
    abstract fun bindContext(application: Application): Context


    @Module
    companion object {

        const val NAME = "cache-the-movie-db"

        @Provides
        @Singleton
        @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideResources(context: Context): Resources = context.resources

    }
}