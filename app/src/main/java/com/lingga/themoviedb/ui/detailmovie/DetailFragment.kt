package com.lingga.themoviedb.ui.detailmovie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
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
        viewModel.getDetail(args.id)
        observe(viewModel.detail) {
            binding.apply {
                data = it
                adapter.submitList(it.genres)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}