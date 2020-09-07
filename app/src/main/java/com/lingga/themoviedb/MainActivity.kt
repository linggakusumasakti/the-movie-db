package com.lingga.themoviedb

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.lingga.themoviedb.databinding.ActivityMainBinding
import com.lingga.themoviedb.ui.base.BaseActivity
import com.lingga.themoviedb.utils.ext.setTransparentStatusBarBlack
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setUpNavigation()
        setTransparentStatusBarBlack()
    }

    private fun setUpNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavBar.setupWithNavController(navController)
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}