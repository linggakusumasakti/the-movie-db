package com.lingga.themoviedb.favorites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.google.android.material.tabs.TabLayoutMediator
import com.lingga.themoviedb.favorites.databinding.FragmentFavoriteBinding
import com.lingga.themoviedb.favorites.di.DaggerFavoriteComponent
import com.lingga.themoviedb.ui.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

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
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(R.string.movie)
                else -> getString(R.string.tv)
            }
        }.attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

}