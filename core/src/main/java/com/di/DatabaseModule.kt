package com.di

import android.content.Context
import androidx.room.Room
import com.data.source.local.room.MovieDao
import com.data.source.local.room.MovieDatabase
import com.data.source.local.room.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

  @Singleton
  @Provides
  fun provideDatabase(context: Context): MovieDatabase =
    Room.databaseBuilder(context, MovieDatabase::class.java, "Movie.db")
      .fallbackToDestructiveMigration().build()

  @Provides
  fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

  @Provides
  fun provideTvShowDao(database: MovieDatabase): TvShowDao = database.tvShowDao()
}