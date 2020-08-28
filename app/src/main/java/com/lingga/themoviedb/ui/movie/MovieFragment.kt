package com.lingga.themoviedb.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lingga.themoviedb.R
import com.lingga.themoviedb.core.data.Resource
import com.lingga.themoviedb.core.domain.model.Movie
import com.lingga.themoviedb.core.ui.BaseFragment
import com.lingga.themoviedb.core.ui.ViewModelFactory
import com.lingga.themoviedb.databinding.FragmentMovieBinding
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import javax.inject.Inject


class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

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
            appbar.textTitle.text = getString(R.string.movie)
            recyclerViewMovie.apply {
                adapter = this@MovieFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun subscribeUi() {
        observe(viewModel.movie) { movie ->
            binding.apply {
                when (movie) {
                    is Resource.Loading -> loading.progressBar.show()
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(movie.data)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        viewError.errorContainer.show()
                        viewError.errorMessage.text =
                            movie.message ?: getString(R.string.oopss_something_went_wrong)
                    }
                }
            }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                movie.id ?: 0
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}