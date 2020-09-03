package com.lingga.themoviedb.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val loading = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = loading

    protected fun setLoading() = loading.postValue(true)

    protected fun finishLoading() = loading.postValue(false)

}