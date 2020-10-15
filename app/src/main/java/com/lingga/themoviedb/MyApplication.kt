package com.lingga.themoviedb

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.lingga.themoviedb.di.AppComponent
import com.lingga.themoviedb.di.DaggerAppComponent
import com.lingga.themoviedb.utils.Cache
import javax.inject.Inject

open class MyApplication : Application() {

    @Inject
    lateinit var cache:Cache

    override fun onCreate() {
        super.onCreate()
        darkTheme()
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

    private fun darkTheme() {
        if (cache.loadDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}