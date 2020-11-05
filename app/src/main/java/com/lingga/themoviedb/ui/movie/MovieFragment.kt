package com.lingga.themoviedb.ui.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.Resource
import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentMovieBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
            appbar.apply {
                textTitle.text = getString(R.string.movie)
                buttonSetting.setOnClickListener { navigateToSetting() }
            }
            recyclerViewMovie.apply {
                adapter = this@MovieFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            searchMovie(this)
        }
    }

    private fun subscribeUi() {
        observe(viewModel.movie ?: return) { movie ->
            binding.apply {
                when (movie) {
                    is Resource.Loading -> loading.progressBar.show()
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(movie.data)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (movie.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                movie.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            adapter.submitList(movie.data)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie)
        )
    }

    private fun navigateToSetting() {
        findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToSettingFragment())
    }

    private fun searchMovie(binding: FragmentMovieBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToSearchMovieFragment(
                        query
                    )
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}