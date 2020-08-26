package com.lingga.themoviedb

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.lingga.themoviedb.core.ui.BaseActivity
import com.lingga.themoviedb.databinding.ActivityMainBinding
import com.lingga.themoviedb.utils.ext.setTransparentStatusBarBlack


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setUpNavigation()
        setTransparentStatusBarBlack()
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavBar.setupWithNavController(navController)
    }
}