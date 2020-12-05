package com.lingga.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.lingga.themoviedb.databinding.ActivityMainBinding
import com.lingga.themoviedb.ui.base.BaseActivity
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.setTransparentStatusBar
import com.lingga.themoviedb.utils.ext.setTransparentStatusBarBlack
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setUpNavigation()
        hideBottomNavigation()
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) setTransparentStatusBar()
        else setTransparentStatusBarBlack()
    }

    private fun setUpNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavBar.setupWithNavController(navController)
    }

    private fun hideBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieFragment -> binding.bottomNavBar.show()
                R.id.tvFragment -> binding.bottomNavBar.show()
                R.id.detailTvShowFragment -> binding.bottomNavBar.hide()
                R.id.favoriteFragment -> binding.bottomNavBar.show()
                R.id.detailFragment -> binding.bottomNavBar.hide()
                R.id.moviePopularFragment -> binding.bottomNavBar.show()
            }
        }
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}