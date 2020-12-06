package com.lingga.themoviedb.favorites

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.domain.model.TvShow
import com.lingga.themoviedb.favorites.databinding.FragmentFavoriteTvShowBinding
import com.lingga.themoviedb.favorites.di.DaggerFavoriteComponent
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.android.synthetic.main.view_empty_favorite.view.*
import javax.inject.Inject

class FavoriteTvShowFragment :
    BaseFragment<FragmentFavoriteTvShowBinding>(R.layout.fragment_favorite_tv_show) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteTvShowViewModel by viewModels { factory }

    private val adapter by lazy { FavoriteTvShowAdapter { navigateToDetail(it) } }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewFavoriteTvShow.apply {
                adapter = this@FavoriteTvShowFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.favoriteTvShow ?: return) {
            if (it.isNullOrEmpty()) {
                binding.animation.show()
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
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }
}