package com.lingga.themoviedb.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.FragmentEditProfileBinding
import com.lingga.themoviedb.ui.base.BaseFragment
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding>(R.layout.fragment_edit_profile) {

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            appbar.apply {
                backButton.apply {
                    show()
                    setOnClickListener { findNavController().popBackStack() }
                }
                textTitle.text = getString(R.string.edit_profile)
                buttonSetting.hide()
            }
            profileName.setText(if (user?.displayName.isNullOrEmpty()) "User" else user?.displayName)
            buttonSave.setOnClickListener { updateProfileName() }
            setImage(this)
        }
    }

    private fun getUrlPhoto(): String {
        var url: String? = null
        for (userInfo in user?.providerData.orEmpty()) {
            url = userInfo.photoUrl.toString()
        }
        return url.toString()
    }

    private fun setImage(binding: FragmentEditProfileBinding) {
        binding.apply {
            if (getUrlPhoto().contains(".jpg") || getUrlPhoto().contains(".png") || getUrlPhoto().contains(
                    "jpeg"
                )
            ) {
                Glide.with(profileImage.context ?: return)
                    .load(getUrlPhoto())
                    .into(profileImage)
            } else {
                Glide.with(profileImage.context ?: return)
                    .load(R.drawable.ic_twotone_account)
                    .into(profileImage)
            }
        }
    }

    private fun updateProfileName() {
        binding.loading.show()
        try {
            user?.updateProfile(userProfileChangeRequest {
                displayName = binding.profileName.text.toString()
            })?.addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.loading.hide()
                    findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            binding.loading.hide()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}