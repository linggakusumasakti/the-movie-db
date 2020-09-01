package com.lingga.themoviedb.favorites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lingga.themoviedb.di.ViewModelKey
import com.lingga.themoviedb.favorites.FavoriteMovieViewModel
import com.lingga.themoviedb.favorites.FavoriteTvShowViewModel
import com.lingga.themoviedb.ui.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class FavoriteViewModelModule {

    @Binds
    abstract fun bindFavoriteViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMovieViewModel::class)
    abstract fun bindFavoriteMovieViewModel(viewModel: FavoriteMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteTvShowViewModel::class)
    abstract fun bindFavoriteTvShowViewModel(viewModel: FavoriteTvShowViewModel): ViewModel
}