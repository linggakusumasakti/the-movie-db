package com.lingga.themoviedb.ui.tvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentTvBinding
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
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
            appbar.textTitle.text = getString(R.string.tv_show)
            recyclerViewTv.apply {
                adapter = this@TvFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.tvShow ?: return) { tvShow ->
            binding.apply {
                when (tvShow) {
                    is Resource.Loading -> loading.progressBar.show()
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(tvShow.data)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        viewError.errorContainer.show()
                        viewError.errorMessage.text =
                            tvShow.message ?: getString(R.string.oopss_something_went_wrong)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}