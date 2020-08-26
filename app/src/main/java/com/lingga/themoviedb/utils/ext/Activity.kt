@file:Suppress("DEPRECATION")

package com.lingga.themoviedb.utils.ext

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.removeObservers(this)
    liveData.observe(this, Observer(body))
}

fun Activity.setTransparentStatusBarBlack() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = window.decorView.systemUiVisibility
        flags = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags
        window.statusBarColor = Color.TRANSPARENT
    }
}