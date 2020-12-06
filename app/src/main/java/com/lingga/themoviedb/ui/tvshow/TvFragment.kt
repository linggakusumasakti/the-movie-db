package com.lingga.themoviedb.ui.tvshow

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.Resource
import com.domain.model.TvShow
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentTvBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class TvFragment : BaseFragment<FragmentTvBinding>(R.layout.fragment_tv) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvShowViewModel by viewModels { factory }

    private val adapter by lazy { TvAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            appbar.apply {
                textTitle.text = getString(R.string.tv_show)
                buttonSetting.setOnClickListener { navigateToSetting() }
            }
            recyclerViewTv.apply {
                adapter = this@TvFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            searchTvShow(this)
        }
    }

    private fun subscribeUi() {
        observe(viewModel.tvShow("popular") ?: return) { tvShow ->
            binding.apply {
                when (tvShow) {
                    is Resource.Loading -> loading.progressBar.show()
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(tvShow.data)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (tvShow.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                tvShow.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            adapter.submitList(tvShow.data)
                        }
                    }
                }
            }
        }

        observe(viewModel.airingTodayTvShow("airing") ?: return) { tvShow ->
            Log.d("cekaringtv", tvShow.data.toString())
        }
    }

    private fun navigateToDetail(tvShow: TvShow) {
        findNavController().navigate(
            TvFragmentDirections.actionTvFragmentToDetailTvShowFragment(tvShow)
        )
    }

    private fun navigateToSetting() {
        findNavController().navigate(TvFragmentDirections.actionTvFragmentToSettingFragment())
    }

    private fun searchTvShow(binding: FragmentTvBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    TvFragmentDirections.actionTvFragmentToSearchTvShowFragment(query)
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}