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


@ExperimentalCoroutinesApi
class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieViewModel by viewModels { factory }

    private val adapter by lazy { MovieHomeAdapter { navigateToDetail(it) } }

    private val adapterNowPlaying by lazy { MovieNowPlayingAdapter { navigateToDetail(it) } }

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
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            recyclerViewNowPlayingMovie.apply {
                adapter = this@MovieFragment.adapterNowPlaying
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
            }
            searchMovie(this)
        }
    }

    private fun subscribeUi() {
        observe(viewModel.movie("popular") ?: return) { movie ->
            Log.d("cekmovie", movie.data.toString())
            binding.apply {
                when (movie) {
                    is Resource.Loading -> {
                        loading.progressBar.show()
                        labelMoviePopular.hide()
                        labelNowPlayingMovie.hide()
                        seeMorePopularMovie.hide()
                    }
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        adapter.submitList(movie.data ?: return@apply)
                        labelMoviePopular.show()
                        labelNowPlayingMovie.show()
                        seeMorePopularMovie.show()
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (movie.data.isNullOrEmpty()) {
                            viewError.errorContainer.show()
                            viewError.errorMessage.text =
                                movie.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            adapter.submitList(movie.data ?: return@apply)
                        }
                    }
                }
            }
        }
        observe(viewModel.nowPlayingMovie("nowplaying") ?: return) { movie ->
            Log.d("cekmovienowplay", movie.data.toString())
            binding.apply {
                when (movie) {
                    is Resource.Loading -> {
                        loading.progressBar.show()
                        labelMoviePopular.hide()
                        labelNowPlayingMovie.hide()
                        seeMoreNowPlayingMovie.hide()
                    }
                    is Resource.Success -> {
                        loading.progressBar.hide()
                        labelMoviePopular.show()
                        labelNowPlayingMovie.show()
                        seeMoreNowPlayingMovie.show()
                        adapterNowPlaying.submitList(movie.data ?: return@apply)
                    }
                    is Resource.Error -> {
                        loading.progressBar.hide()
                        if (movie.data.isNullOrEmpty()) {
                            viewError.errorContainer.hide()
                            viewError.errorMessage.text =
                                movie.message ?: getString(R.string.oopss_something_went_wrong)
                        } else {
                            adapterNowPlaying.submitList(movie.data ?: return@apply)
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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}