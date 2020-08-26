package com.lingga.themoviedb.di

import com.lingga.themoviedb.MainActivity
import com.lingga.themoviedb.core.di.CoreComponent
import com.lingga.themoviedb.favorite.FavoriteFragment
import com.lingga.themoviedb.movie.MovieFragment
import com.lingga.themoviedb.tvshow.TvFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvFragment)
    fun inject(fragment: FavoriteFragment)
}