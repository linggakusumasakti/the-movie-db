package com.lingga.themoviedb.ui.profile

import android.content.Context
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentProfileBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}