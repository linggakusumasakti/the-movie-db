package com.lingga.themoviedb.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.domain.model.Movie
import com.domain.model.TvShow
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentProfileBinding
import com.lingga.themoviedb.databinding.ItemListMovieFireStoreBinding
import com.lingga.themoviedb.databinding.ItemListTvShowFireStoreBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.show
import com.utils.Constant
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    private val db by lazy { Firebase.firestore }

    private lateinit var adapter: FirestoreRecyclerAdapter<Movie, MovieFireStoreViewHolder>

    private lateinit var adapterTvShow: FirestoreRecyclerAdapter<TvShow, TvShowFireStoreViewHolder>

    companion object {
        const val TAB_MOVIE = 0
        const val TAB_TV = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            profileName.text = if (user?.displayName.isNullOrEmpty()) "User" else user?.displayName
            buttonEditProfile.setOnClickListener { navigateToEditProfile() }
            setImage(this)
            setTabLayout(this)
            setUpAdapterMovie(this)
            setUpAdapterTvShow(this)
        }
    }

    private fun setImage(binding: FragmentProfileBinding) {
        binding.apply {
            if (user?.photoUrl != null) {
                Glide.with(profileImage.context ?: return)
                    .load(user?.photoUrl)
                    .into(profileImage)
            } else {
                Glide.with(profileImage.context ?: return)
                    .load(R.drawable.ic_twotone_account)
                    .into(profileImage)
            }
        }
    }

    private fun navigateToEditProfile() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
    }

    private fun setTabLayout(binding: FragmentProfileBinding) {
        binding.apply {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        when (tab.position) {
                            TAB_MOVIE -> {
                                binding.apply {
                                    recyclerViewMovie.show()
                                    recyclerViewTvShow.hide()
                                }
                            }
                            TAB_TV -> {
                                binding.apply {
                                    recyclerViewMovie.hide()
                                    recyclerViewTvShow.show()
                                }
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setUpAdapterMovie(binding: FragmentProfileBinding) {
        val query = db.collection(Constant.PATH_MOVIE).document(Constant.PATH_FAVORITES)
            .collection(user?.uid.toString())
        val response = FirestoreRecyclerOptions.Builder<Movie>()
            .setQuery(query, Movie::class.java)
            .build()

        adapter = object :
            FirestoreRecyclerAdapter<Movie, MovieFireStoreViewHolder>(response) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): MovieFireStoreViewHolder = MovieFireStoreViewHolder(
                ItemListMovieFireStoreBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )

            override fun onBindViewHolder(
                holder: MovieFireStoreViewHolder,
                position: Int,
                model: Movie
            ) {
                holder.apply {
                    bind(model)
                    itemView.setOnClickListener { navigateToDetailMovie(model) }
                }
            }
        }
        adapter.notifyDataSetChanged()
        binding.recyclerViewMovie.apply {
            adapter = this@ProfileFragment.adapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setUpAdapterTvShow(binding: FragmentProfileBinding) {
        val query = db.collection(Constant.PATH_TV).document(Constant.PATH_FAVORITES)
            .collection(user?.uid.toString())
        val response = FirestoreRecyclerOptions.Builder<TvShow>()
            .setQuery(query, TvShow::class.java)
            .build()

        adapterTvShow = object :
            FirestoreRecyclerAdapter<TvShow, TvShowFireStoreViewHolder>(response) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): TvShowFireStoreViewHolder = TvShowFireStoreViewHolder(
                ItemListTvShowFireStoreBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )

            override fun onBindViewHolder(
                holder: TvShowFireStoreViewHolder,
                position: Int,
                model: TvShow
            ) {
                holder.apply {
                    bind(model)
                    itemView.setOnClickListener { navigateToDetailTvShow(model) }
                }
            }
        }
        adapterTvShow.notifyDataSetChanged()
        binding.recyclerViewTvShow.apply {
            adapter = this@ProfileFragment.adapterTvShow
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun navigateToDetailMovie(movie: Movie) {
        findNavController().navigate(
            ProfileFragmentDirections.actionProfileFragmentToDetailFragment(
                movie
            )
        )
    }

    private fun navigateToDetailTvShow(tvShow: TvShow) {
        findNavController().navigate(
            ProfileFragmentDirections.actionProfileFragmentToDetailTvShowFragment(
                tvShow
            )
        )
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
        adapterTvShow.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
        adapterTvShow.stopListening()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}