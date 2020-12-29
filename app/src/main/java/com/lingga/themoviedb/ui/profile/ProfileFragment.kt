package com.lingga.themoviedb.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentProfileBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            profileName.text = if (user?.displayName.isNullOrEmpty()) "User" else user?.displayName
            buttonEditProfile.setOnClickListener { navigateToEditProfile() }
            setImage(this)
        }
    }

    private fun setImage(binding: FragmentProfileBinding) {
        binding.apply {
            if (user?.photoUrl != null) {
                Glide.with(profileImage.context ?: return)
                    .load(user?.photoUrl)
                    .into(profileImage)
                Log.d("cek", "sukses , ${user?.photoUrl}")
            } else {
                Glide.with(profileImage.context ?: return)
                    .load(R.drawable.ic_twotone_account)
                    .into(profileImage)
                Log.d("cek", "gagal")
            }
        }
    }

    private fun navigateToEditProfile() {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

}