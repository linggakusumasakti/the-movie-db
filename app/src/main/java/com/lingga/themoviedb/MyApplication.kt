package com.lingga.themoviedb

import android.app.Application
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.lingga.themoviedb.di.AppComponent
import com.lingga.themoviedb.di.DaggerAppComponent

open class MyApplication : Application() {

  private val coreComponent: CoreComponent by lazy {
    DaggerCoreComponent.factory().create(applicationContext)
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory().create(coreComponent)
  }
}