package com.lingga.themoviedb.ui.detailtvshow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentDetailTvShowBinding
import com.lingga.themoviedb.ui.ViewModelFactory
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.ui.detailmovie.CreditAdapter
import com.lingga.themoviedb.ui.detailmovie.GenreAdapter
import com.lingga.themoviedb.utils.ext.observe
import com.utils.Constant
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

    private val db by lazy { Firebase.firestore }

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    private val collection by lazy {
        db.collection(Constant.PATH_TV).document(Constant.PATH_FAVORITES)
            .collection(user?.uid.toString())
            .document(args.tvShow.id.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            args = this@DetailTvShowFragment.args.tvShow
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailTvShowFragment.genreAdapter
            }
            recyclerViewCredit.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailTvShowFragment.creditAdapter
            }
            backButton.setOnClickListener { findNavController().navigateUp() }
            setFavorite(this)
        }
    }

    private fun subscribeUi() {
        val id = args.tvShow.id ?: 0
        viewModel.getDetail(id)
        viewModel.getCreditTvShow(id)
        observe(viewModel.detail) { tvShow ->
            genreAdapter.submitList(tvShow.genres)
        }
        observe(viewModel.credit) { credit ->
            creditAdapter.submitList(credit)
        }
        /**
         * use if want to save favorite to local
         */
        /* viewModel.getTvShowById(id)
         observe(viewModel.detailDb) { tvShow ->
             var statusFavorite = tvShow.isFavorite ?: false
             setStatusFavorite(statusFavorite)
             binding.favoriteButton.setOnClickListener {
                 statusFavorite = !statusFavorite
                 viewModel.setFavoriteTvShow(args.tvShow, statusFavorite)
                 setStatusFavorite(statusFavorite)
             }
         }*/
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

    private fun setFavorite(binding: FragmentDetailTvShowBinding) {
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

    private fun addDataFireStore(status: Boolean) {
        val tvShow = hashMapOf(
            "id" to args.tvShow.id,
            "name" to args.tvShow.name,
            "backdrop" to args.tvShow.backdropPath,
            "overview" to args.tvShow.overview,
            "poster" to args.tvShow.posterPath,
            "firstAirDate" to args.tvShow.firstAirDate,
            "voteAverage" to args.tvShow.voteAverage,
            "isFavorite" to status
        )

        collection.set(tvShow)
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}