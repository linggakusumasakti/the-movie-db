package com.lingga.themoviedb.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.di.CoreComponent
import com.di.DaggerCoreComponent
import com.domain.model.Movie
import com.lingga.themoviedb.favorites.databinding.FragmentFavoriteMovieBinding
import com.lingga.themoviedb.favorites.di.DaggerFavoriteComponent
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.android.synthetic.main.view_empty_favorite.view.*
import javax.inject.Inject

class FavoriteMovieFragment :
    BaseFragment<FragmentFavoriteMovieBinding>(R.layout.fragment_favorite_movie) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteMovieViewModel by viewModels { factory }

    private val adapter by lazy { FavoriteAdapter { navigateToDetail(it) } }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewFavoriteMovie.apply {
                adapter = this@FavoriteMovieFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.favoriteMovie ?: return) {
            Log.d("cekfav", it.toString())
            if (it.isNullOrEmpty()) binding.animation.show()
            else adapter.submitList(it)
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(movie)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }
}