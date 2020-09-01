package com.di

import android.content.Context
import com.domain.repository.IMovieRepository
import com.domain.repository.ITvShowRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [RepositoryModule::class]
)
interface CoreComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): CoreComponent
  }

  fun provideRepository(): IMovieRepository
  fun provideTvShowRepository(): ITvShowRepository
}