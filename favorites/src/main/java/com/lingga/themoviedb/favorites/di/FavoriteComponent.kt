package com.lingga.themoviedb.favorites.di

import com.di.CoreComponent
import com.lingga.themoviedb.di.AppModule
import com.lingga.themoviedb.favorites.FavoriteFragment
import com.lingga.themoviedb.favorites.FavoriteMovieFragment
import com.lingga.themoviedb.favorites.FavoriteTvShowFragment
import dagger.Component

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteViewModelModule::class, AppModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)
}