package com.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UtilModule {

    companion object {

        const val NAME = "cache-the-movie-db"

        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun provideResources(context: Context): Resources = context.resources

    }
}