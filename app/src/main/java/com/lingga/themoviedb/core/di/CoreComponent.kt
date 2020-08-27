package com.lingga.themoviedb.core.di

import android.content.Context
import com.lingga.themoviedb.core.domain.repository.IMovieRepository
import com.lingga.themoviedb.core.domain.repository.ITvShowRepository
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