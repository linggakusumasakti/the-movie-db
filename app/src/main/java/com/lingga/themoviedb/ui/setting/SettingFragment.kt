package com.lingga.themoviedb.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentSettingBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.Cache
import com.lingga.themoviedb.utils.ext.hide
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    @Inject
    lateinit var cache: Cache

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            appbar.apply {
                buttonSetting.hide()
                textTitle.text = getString(R.string.setting)
                changeLanguage.setOnClickListener { doChangeLanguage() }
            }
            doChangeTheme(this)
        }
    }

    private fun doChangeLanguage() {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(intent)
    }

    private fun doChangeTheme(binding: FragmentSettingBinding) {
        binding.apply {
            switchTheme.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cache.saveDarkTheme(true)
                } else {
                    cache.saveDarkTheme(false)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}