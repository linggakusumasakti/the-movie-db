package com.lingga.themoviedb.ui.detailtvshow

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentDetailTvShowBinding
import com.lingga.themoviedb.utils.ext.observe
import javax.inject.Inject

class DetailTvShowFragment :
    BaseFragment<FragmentDetailTvShowBinding>(R.layout.fragment_detail_tv_show) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailTvShowViewModel by viewModels { factory }

    private val args: DetailTvShowFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
        observe(viewModel.detail(args.id)) {
            Log.d("cekdetailtv", it.data.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}