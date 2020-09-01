package com.lingga.themoviedb.ui.detailmovie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentDetailBinding
import com.lingga.themoviedb.utils.ext.observe
import javax.inject.Inject


class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }

    private val args: DetailFragmentArgs by navArgs()

    private val adapter by lazy { GenreAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDetailArgs()
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.adapter
            }
            backButton.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun subscribeUi() {
        viewModel.getDetail(args.movie.id ?: 0)
        observe(viewModel.detail) { movie ->
            adapter.submitList(movie.genres)
        }
    }

    private fun showDetailArgs() {
        binding.args = args.movie
        var statusFavorite = args.movie.isFavorite ?: false
        setStatusFavorite(statusFavorite)
        binding.favoriteButton.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteMovie(args.movie, statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.apply {
            if (statusFavorite) {
                favoriteButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        context ?: return,
                        R.drawable.ic_baseline_favorite
                    )
                )
            } else {
                favoriteButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        context ?: return,
                        R.drawable.ic_twotone_favorite_border
                    )
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}