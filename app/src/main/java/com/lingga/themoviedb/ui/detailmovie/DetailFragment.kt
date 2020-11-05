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
import com.lingga.themoviedb.databinding.FragmentDetailBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }

    private val args: DetailFragmentArgs by navArgs()

    private val adapterGenre by lazy { GenreAdapter() }

    private val adapterCredit by lazy { CreditAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            args = this@DetailFragment.args.movie
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.adapterGenre
            }
            recyclerViewCredit.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailFragment.adapterCredit
            }
            backButton.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun subscribeUi() {
        viewModel.getDetail(args.movie.id ?: 0)
        viewModel.getCredit(args.movie.id ?: 0)
        viewModel.getDetailDb(args.movie.id ?: 0)
        observe(viewModel.detail) { movie ->
            adapterGenre.submitList(movie.genres)
        }
        observe(viewModel.credit) { credit ->
            adapterCredit.submitList(credit)
        }
        observe(viewModel.detailDb) { movie ->
            var statusFavorite = movie.isFavorite ?: false
            setStatusFavorite(statusFavorite)
            binding.favoriteButton.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(args.movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
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