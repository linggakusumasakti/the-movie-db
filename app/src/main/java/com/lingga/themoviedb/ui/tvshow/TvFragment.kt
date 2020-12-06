package com.lingga.themoviedb.ui.tvshow

import android.content.Context
import android.os.Bundle
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

    private val adapter by lazy { TvShowHomeAdapter { navigateToDetail(it) } }

    private val adapterAiringToday by lazy { TvShowAiringTodayAdapter { navigateToDetail(it) } }

    private val adapterTopRated by lazy { TvShowTopRatedAdapter { navigateToDetail(it) } }

    companion object {
        const val POPULAR = "popular"
        const val AIRING = "airing"
        const val TOP_RATED = "top_rated"
    }

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
            recyclerViewTvShow.apply {
                adapter = this@TvFragment.adapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerViewAiringTodayTvShow.apply {
                adapter = this@TvFragment.adapterAiringToday
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerViewTopRatedTvShow.apply {
                adapter = this@TvFragment.adapterTopRated
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            seeMorePopularTvShow.setOnClickListener { navigateToListSeeMoreTvShow(POPULAR) }
            seeMoreAiringTodayTvShow.setOnClickListener { navigateToListSeeMoreTvShow(AIRING) }
            searchTvShow(this)
        }
    }

    private fun subscribeUi() {
        observe(viewModel.tvShow(POPULAR) ?: return) { tvShow ->
            binding.apply {
                when (tvShow) {
                    is Resource.Loading -> isLoading(this)
                    is Resource.Success -> {
                        isSuccess(this)
                        adapter.submitList(tvShow.data ?: return@apply)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (tvShow.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                tvShow.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            isSuccess(this)
                            adapter.submitList(tvShow.data ?: return@apply)
                        }
                    }
                }
            }
        }

        observe(viewModel.airingTodayTvShow(AIRING) ?: return) { tvShow ->
            binding.apply {
                when (tvShow) {
                    is Resource.Loading -> isLoading(this)
                    is Resource.Success -> {
                        isSuccess(this)
                        adapterAiringToday.submitList(tvShow.data ?: return@apply)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (tvShow.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                tvShow.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            isSuccess(this)
                            adapterAiringToday.submitList(tvShow.data ?: return@apply)
                        }
                    }
                }
            }
        }

        observe(viewModel.latestTvShow(TOP_RATED) ?: return) { tvShow ->
            binding.apply {
                when (tvShow) {
                    is Resource.Loading -> isLoading(this)
                    is Resource.Success -> {
                        isSuccess(this)
                        adapterTopRated.submitList(tvShow.data ?: return@apply)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (tvShow.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                tvShow.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            isSuccess(this)
                            adapterTopRated.submitList(tvShow.data ?: return@apply)
                        }
                    }
                }
            }
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

    private fun navigateToListSeeMoreTvShow(type: String?) {
        findNavController().navigate(
            TvFragmentDirections.actionTvFragmentToTvShowPopularFragment(
                type
            )
        )
    }

    private fun isLoading(binding: FragmentTvBinding) {
        binding.apply {
            loading.progressBar.show()
            labelAiringTodayTvShow.hide()
            labelTvShowPopular.hide()
            labelTopRatedTvShow.hide()
            seeMoreAiringTodayTvShow.hide()
            seeMorePopularTvShow.hide()
            seeMoreTopRatedtvShow.hide()
        }
    }

    private fun isSuccess(binding: FragmentTvBinding) {
        binding.apply {
            loading.progressBar.hide()
            labelAiringTodayTvShow.show()
            labelTvShowPopular.show()
            labelTopRatedTvShow.show()
            seeMoreAiringTodayTvShow.show()
            seeMorePopularTvShow.show()
            seeMoreTopRatedtvShow.show()
        }
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