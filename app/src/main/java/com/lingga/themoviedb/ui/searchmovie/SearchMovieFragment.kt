package com.lingga.themoviedb.ui.searchmovie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.domain.model.Movie
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentSearchMovieBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.ui.movie.MovieAdapter
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.observe
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(R.layout.fragment_search_movie) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: SearchMovieViewModel by viewModels { factory }

    private val args: SearchMovieFragmentArgs by navArgs()

    private val adapter by lazy { MovieAdapter { navigateToDetail(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            appbar.textTitle.text = getString(R.string.movie)
            recyclerViewMovie.apply {
                adapter = this@SearchMovieFragment.adapter
                layoutManager = LinearLayoutManager(context)
            }
            searchMovie(this)
        }
    }

    private fun subscribeUi() {
        viewModel.getSearch(args.query ?: "")
        observe(viewModel.search) {
            if (it.isNullOrEmpty()) binding.notFound.show()
            else adapter.submitList(it)
        }
        observe(viewModel.getLoading()) {
            binding.loading.progressBar.apply {
                if (it) show() else hide()
            }
        }
        observe(viewModel.error) {
            binding.viewError.apply {
                errorContainer.show()
                errorMessage.text = it
            }
        }
    }

    private fun searchMovie(binding: FragmentSearchMovieBinding) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    SearchMovieFragmentDirections.actionSearchMovieFragmentSelf(query)
                )
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            SearchMovieFragmentDirections.actionSearchMovieFragmentToDetailFragment(movie)
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}