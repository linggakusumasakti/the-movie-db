package com.lingga.themoviedb.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}