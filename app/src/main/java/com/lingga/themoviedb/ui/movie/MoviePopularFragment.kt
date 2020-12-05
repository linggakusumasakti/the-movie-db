package com.lingga.themoviedb.ui.movie

import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentMoviePopularBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MoviePopularFragment :
    BaseFragment<FragmentMoviePopularBinding>(R.layout.fragment_movie_popular) {


    override fun onDetach() {
        super.onDetach()
        appComponent.inject(this)
    }
}