package com.lingga.themoviedb.core.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lingga.themoviedb.MyApplication
import com.lingga.themoviedb.di.AppComponent

abstract class BaseActivity<B : ViewDataBinding> constructor(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected open lateinit var binding: B
    protected open lateinit var appComponent: AppComponent

    private fun bindView() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MyApplication).appComponent
        super.onCreate(savedInstanceState)
        bindView()
    }

}
