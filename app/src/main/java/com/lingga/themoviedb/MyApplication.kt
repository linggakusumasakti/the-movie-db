package com.lingga.themoviedb

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.lingga.themoviedb.di.AppComponent
import com.lingga.themoviedb.di.DaggerAppComponent
import com.utils.Cache
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
open class MyApplication : Application() {

    @Inject
    lateinit var cache: Cache


    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.factory().create(coreComponent).inject(this)
        darkTheme()
    }

    private fun darkTheme() {
        if (cache.loadDarkTheme()) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}