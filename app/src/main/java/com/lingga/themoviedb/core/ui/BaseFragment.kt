package com.lingga.themoviedb.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lingga.themoviedb.MyApplication
import com.lingga.themoviedb.di.AppComponent

abstract class BaseFragment<B : ViewDataBinding> constructor(
  @LayoutRes val layoutRes: Int
) : Fragment() {

  protected lateinit var binding: B
  protected lateinit var appComponent: AppComponent

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    return binding.root
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    appComponent = (requireActivity().application as MyApplication).appComponent
  }

}