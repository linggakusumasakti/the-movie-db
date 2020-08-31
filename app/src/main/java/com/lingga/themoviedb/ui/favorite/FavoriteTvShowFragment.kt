package com.lingga.themoviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.domain.model.TvShow
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentFavoriteTvShowBinding
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import javax.inject.Inject

class FavoriteTvShowFragment :
    BaseFragment<FragmentFavoriteTvShowBinding>(R.layout.fragment_favorite_tv_show) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteTvShowViewModel by viewModels { factory }

    private val adapter by lazy { FavoriteTvShowAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewMovie.apply {
                adapter = this@FavoriteTvShowFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.favoriteTvShow) {
            if (it.isNullOrEmpty()) {
                binding.emptyFavorite.textView.apply {
                    show()
                    text = getString(R.string.empty_fav_tv_show)
                }
            } else adapter.submitList(it)
        }
    }

    private fun navigateToDetail(tvShow: TvShow) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvShowFragment(tvShow)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}