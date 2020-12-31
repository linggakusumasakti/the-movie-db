package com.lingga.themoviedb.ui.detailmovie

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentDetailBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.observe
import com.utils.Constant
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

    private val db by lazy { Firebase.firestore }

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    private val collection by lazy {
        db.collection(Constant.PATH_MOVIE).document(Constant.PATH_FAVORITES)
            .collection(user?.uid.toString())
            .document(args.movie.id.toString())
    }

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
            backButton.setOnClickListener { findNavController().popBackStack() }
            buyTikcetButton.setOnClickListener { navigateToBuyTicket() }
            setFavorite(this)
        }
    }

    private fun subscribeUi() {
        val id = args.movie.id ?: 0
        viewModel.getDetail(id)
        viewModel.getCredit(id)
        observe(viewModel.detail) { movie ->
            adapterGenre.submitList(movie.genres)
        }
        observe(viewModel.credit) { credit ->
            adapterCredit.submitList(credit)
        }

        /**
         *  use if want to save favorite to local
         */
        /* viewModel.getDetailDb(id)
             observe(viewModel.detailDb) { movie ->
                 var statusFavorite = movie.isFavorite ?: false
                 setStatusFavorite(statusFavorite)
                 binding.favoriteButton.setOnClickListener {
                     statusFavorite = !statusFavorite
                     viewModel.setFavoriteMovie(args.movie, statusFavorite)
                     setStatusFavorite(statusFavorite)
                     addDataFireStore()
                 }
             }*/
    }

    private fun setFavorite(binding: FragmentDetailBinding) {
        collection.get()
            .addOnSuccessListener { result ->
                var statusFavorite = result.getBoolean("isFavorite") ?: false
                setStatusFavorite(statusFavorite)
                binding.favoriteButton.setOnClickListener {
                    statusFavorite = !statusFavorite
                    setStatusFavorite(statusFavorite)
                    if (statusFavorite) addDataFireStore(statusFavorite) else deleteFavorite()
                }
            }
    }

    private fun deleteFavorite() {
        collection.delete()
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

    private fun navigateToBuyTicket() {
        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
    }

    private fun addDataFireStore(status: Boolean) {
        val movie = hashMapOf(
            "title" to args.movie.title,
            "backdrop" to args.movie.backdropPath,
            "overview" to args.movie.overview,
            "popularity" to args.movie.popularity,
            "poster" to args.movie.posterPath,
            "releaseDate" to args.movie.releaseDate,
            "voteAverage" to args.movie.voteAverage,
            "isFavorite" to status
        )

        collection.set(movie)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}