package com.lingga.themoviedb.tvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.databinding.FragmentTvBinding

class TvFragment : BaseFragment<FragmentTvBinding>(R.layout.fragment_tv) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            appbar.textTitle.text = getString(R.string.tv_show)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}