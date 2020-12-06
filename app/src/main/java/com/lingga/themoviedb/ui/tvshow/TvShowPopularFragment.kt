package com.lingga.themoviedb.ui.tvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.Resource
import com.domain.model.TvShow
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentTvShowPopularBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class TvShowPopularFragment :
    BaseFragment<FragmentTvShowPopularBinding>(R.layout.fragment_tv_show_popular) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvShowViewModel by viewModels { factory }

    private val adapter by lazy { TvAdapter { navigateToDetail(it) } }

    private val args: TvShowPopularFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            appbar.textTitle.text = getTitleAppBar()
            recyclerViewPopularTvShow.apply {
                adapter = this@TvShowPopularFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            appbar.buttonSetting.setOnClickListener { navigateToSetting() }
        }
    }

    private fun getTitleAppBar(): String =
        when (args.type) {
            TvFragment.POPULAR -> getString(R.string.popular_tv_show)
            TvFragment.AIRING -> getString(R.string.airing_today_tv_show)
            TvFragment.TOP_RATED -> getString(R.string.top_rated_tv_show)
            else -> ""
        }

    private fun subscribeUi() {
        when (args.type) {
            TvFragment.POPULAR -> {
                observe(viewModel.tvShow(TvFragment.POPULAR) ?: return) {
                    binding.apply {
                        when (it) {
                            is Resource.Loading -> loading.progressBar.show()
                            is Resource.Success -> {
                                loading.progressBar.hide()
                                adapter.submitList(it.data)
                            }
                            is Resource.Error -> {
                                loading.progressBar.hide()
                                if (it.data.isNullOrEmpty()) {
                                    viewError.errorContainer.show()
                                    viewError.errorMessage.text =
                                        it.message ?: getString(R.string.oopss_something_went_wrong)
                                } else adapter.submitList(it.data)
                            }
                        }
                    }
                }
            }
            TvFragment.AIRING -> {
                observe(viewModel.airingTodayTvShow(TvFragment.AIRING) ?: return) {
                    binding.apply {
                        when (it) {
                            is Resource.Loading -> loading.progressBar.show()
                            is Resource.Success -> {
                                loading.progressBar.hide()
                                adapter.submitList(it.data)
                            }
                            is Resource.Error -> {
                                loading.progressBar.hide()
                                if (it.data.isNullOrEmpty()) {
                                    viewError.errorContainer.show()
                                    viewError.errorMessage.text =
                                        it.message ?: getString(R.string.oopss_something_went_wrong)
                                } else adapter.submitList(it.data)
                            }
                        }
                    }
                }
            }
            TvFragment.TOP_RATED -> {
                observe(viewModel.latestTvShow(TvFragment.TOP_RATED) ?: return) {
                    binding.apply {
                        when (it) {
                            is Resource.Loading -> loading.progressBar.show()
                            is Resource.Success -> {
                                loading.progressBar.hide()
                                adapter.submitList(it.data)
                            }
                            is Resource.Error -> {
                                loading.progressBar.hide()
                                if (it.data.isNullOrEmpty()) {
                                    viewError.errorContainer.show()
                                    viewError.errorMessage.text =
                                        it.message ?: getString(R.string.oopss_something_went_wrong)
                                } else adapter.submitList(it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetail(tvShow: TvShow) {
        findNavController().navigate(
            TvShowPopularFragmentDirections.actionTvShowPopularFragmentToDetailTvShowFragment(tvShow)
        )
    }

    private fun navigateToSetting() {
        findNavController().navigate(TvShowPopularFragmentDirections.actionTvShowPopularFragmentToSettingFragment())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}