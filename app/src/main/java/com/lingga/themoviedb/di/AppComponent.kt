package com.lingga.themoviedb.di

import com.lingga.themoviedb.MainActivity
import com.lingga.themoviedb.core.di.CoreComponent
import com.lingga.themoviedb.ui.detailmovie.DetailFragment
import com.lingga.themoviedb.ui.detailtvshow.DetailTvShowFragment
import com.lingga.themoviedb.ui.favorite.FavoriteFragment
import com.lingga.themoviedb.ui.movie.MovieFragment
import com.lingga.themoviedb.ui.tvshow.TvFragment
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
    fun inject(fragment: DetailFragment)
    fun inject(fragment: DetailTvShowFragment)
}