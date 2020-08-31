package com.lingga.themoviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentFavoriteMovieBinding
import com.lingga.themoviedb.utils.ext.observe
import javax.inject.Inject

class FavoriteMovieFragment :
    BaseFragment<FragmentFavoriteMovieBinding>(R.layout.fragment_favorite_movie) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteMovieViewModel by viewModels { factory }

    private val adapter by lazy { FavoriteAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewMovie.apply {
                adapter = this@FavoriteMovieFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.favoriteMovie) {
            adapter.submitList(it)
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(movie)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}