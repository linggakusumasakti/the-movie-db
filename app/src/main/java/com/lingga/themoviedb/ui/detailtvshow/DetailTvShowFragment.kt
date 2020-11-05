package com.lingga.themoviedb.ui.detailtvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentDetailTvShowBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.ui.detailmovie.CreditAdapter
import com.lingga.themoviedb.ui.detailmovie.GenreAdapter
import com.lingga.themoviedb.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class DetailTvShowFragment :
    BaseFragment<FragmentDetailTvShowBinding>(R.layout.fragment_detail_tv_show) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailTvShowViewModel by viewModels { factory }

    private val args: DetailTvShowFragmentArgs by navArgs()

    private val genreAdapter by lazy { GenreAdapter() }

    private val creditAdapter by lazy { CreditAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
        showDetailArgs()
    }

    private fun initBinding() {
        binding.apply {
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailTvShowFragment.genreAdapter
            }
            recyclerViewCredit.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailTvShowFragment.creditAdapter
            }
            backButton.setOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun subscribeUi() {
        viewModel.getDetail(args.tvShow.id ?: 0)
        viewModel.getCreditTvShow(args.tvShow.id ?: 0)
        observe(viewModel.detail) { tvShow ->
            genreAdapter.submitList(tvShow.genres)
        }
        observe(viewModel.credit) { credit ->
            creditAdapter.submitList(credit)
        }
    }

    private fun showDetailArgs() {
        binding.args = args.tvShow
        var statusFavorite = args.tvShow.isFavorite ?: false
        setStatusFavorite(statusFavorite)
        binding.favoriteButton.setOnClickListener {
            statusFavorite = !statusFavorite
            viewModel.setFavoriteTvShow(args.tvShow, statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.apply {
            favoriteButton.setImageDrawable(
                getDrawable(
                    requireContext(),
                    if (statusFavorite) R.drawable.ic_baseline_favorite else R.drawable.ic_twotone_favorite_border
                )
            )
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}