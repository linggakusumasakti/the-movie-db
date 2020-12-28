package com.lingga.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.lingga.themoviedb.databinding.ActivityMainBinding
import com.lingga.themoviedb.ui.base.BaseActivity
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.setTransparentStatusBar
import com.lingga.themoviedb.utils.ext.setTransparentStatusBarBlack
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setUpNavigation()
        hideBottomNavigation()
        googleAnalytics()
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
                R.id.searchMovieFragment -> binding.bottomNavBar.show()
                R.id.searchTvShowFragment -> binding.bottomNavBar.show()
                R.id.settingFragment -> binding.bottomNavBar.hide()
                R.id.movieNowPlayingFragment -> binding.bottomNavBar.show()
                R.id.movieUpcomingFragment -> binding.bottomNavBar.show()
                R.id.tvShowPopularFragment -> binding.bottomNavBar.show()
                R.id.profileFragment -> binding.bottomNavBar.show()
                R.id.editProfileFragment -> binding.bottomNavBar.hide()
            }
        }
    }

    private fun googleAnalytics() {
        firebaseAnalytics = Firebase.analytics
        val bundle = Bundle()
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}