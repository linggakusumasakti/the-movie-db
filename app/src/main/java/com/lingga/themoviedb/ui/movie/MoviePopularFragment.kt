package com.lingga.themoviedb.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.Resource
import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentMoviePopularBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MoviePopularFragment :
    BaseFragment<FragmentMoviePopularBinding>(R.layout.fragment_movie_popular) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieViewModel by viewModels { factory }

    private val adapter by lazy { MovieAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        subscribeUi()
    }

    private fun initBinding() {
        binding.apply {
            appbar.textTitle.text = getString(R.string.movie_popular)
            recyclerViewPopularMovie.apply {
                adapter = this@MoviePopularFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            appbar.buttonSetting.setOnClickListener { navigateToSetting() }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.movie("popular") ?: return) {
            binding.apply {
                when (it) {
                    is Resource.Loading -> loading.progressBar.show()
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(it.data)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (it.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                it.message ?: getString(R.string.oopss_something_went_wrong)
                        } else adapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            MoviePopularFragmentDirections.actionMoviePopularFragmentToDetailFragment(movie)
        )
    }

    private fun navigateToSetting() {
        findNavController().navigate(MoviePopularFragmentDirections.actionMoviePopularFragmentToSettingFragment())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}