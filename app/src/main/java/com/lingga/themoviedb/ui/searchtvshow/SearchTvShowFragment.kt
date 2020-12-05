package com.lingga.themoviedb.ui.searchtvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.domain.model.TvShow
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentSearchTvShowBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.ui.tvshow.TvAdapter
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SearchTvShowFragment :
    BaseFragment<FragmentSearchTvShowBinding>(R.layout.fragment_search_tv_show) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: SearchTvShowViewModel by viewModels { factory }

    private val args: SearchTvShowFragmentArgs by navArgs()

    private val adapter by lazy { TvAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            appbar.textTitle.text = getString(R.string.tv_show)
            recyclerViewTvShow.apply {
                adapter = this@SearchTvShowFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            appbar.buttonSetting.setOnClickListener { navigateToSetting() }
            searchTvShow(this)
        }
    }

    private fun subscribeUi() {
        viewModel.getSearch(args.query ?: "")
        observe(viewModel.search) {
            if (it.isNullOrEmpty()) binding.notFound.show()
            else adapter.submitList(it)
        }
        observe(viewModel.getLoading()) {
            binding.loading.progressBar.apply {
                if (it) show() else hide()
            }
        }
        observe(viewModel.error) {
            binding.viewError.apply {
                errorContainer.show()
                errorMessage.text = it
            }
        }
    }

    private fun searchTvShow(binding: FragmentSearchTvShowBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    SearchTvShowFragmentDirections.actionSearchTvShowFragmentSelf(query)
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun navigateToDetail(tvShow: TvShow) {
        findNavController().navigate(
            SearchTvShowFragmentDirections.actionSearchTvShowFragmentToDetailTvShowFragment(tvShow)
        )
    }

    private fun navigateToSetting() {
        findNavController().navigate(SearchTvShowFragmentDirections.actionSearchTvShowFragmentToSettingFragment())
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}