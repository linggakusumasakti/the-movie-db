package com.lingga.themoviedb.di

import androidx.lifecycle.ViewModelProvider
import com.lingga.themoviedb.core.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}