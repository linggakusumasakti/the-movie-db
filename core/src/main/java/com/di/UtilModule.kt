package com.di

import android.content.Context
import android.content.SharedPreferences
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

    }
}