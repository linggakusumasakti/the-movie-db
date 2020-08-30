package com.lingga.themoviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.databinding.FragmentFavoriteTvShowBinding

class FavoriteTvShowFragment :
    BaseFragment<FragmentFavoriteTvShowBinding>(R.layout.fragment_favorite_tv_show) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}