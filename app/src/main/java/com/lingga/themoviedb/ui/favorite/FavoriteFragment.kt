package com.lingga.themoviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPagerAdapter()
        initTabLayout()
    }

    private class FavoritePagerAdapter(
        fm: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> FavoriteMovieFragment()
            else -> FavoriteTvShowFragment()
        }
    }

    private fun initPagerAdapter() {
        val pagerAdapter = FavoritePagerAdapter(childFragmentManager, lifecycle)
        binding.apply {
            viewPager.apply {
                offscreenPageLimit = 2
                adapter = pagerAdapter
            }
            appbar.textTitle.text = getString(R.string.favorite)
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = when (pos) {
                0 -> resources.getString(R.string.movie)
                else -> resources.getString(R.string.tv_show)
            }
        }.attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}