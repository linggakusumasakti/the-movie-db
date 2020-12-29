package com.lingga.themoviedb.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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

    companion object {
        const val RESULT_LOAD_IMAGE = 123
    }

    private val user by lazy { FirebaseAuth.getInstance().currentUser }

    private var uri: Uri? = null

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
            changePhoto.setOnClickListener { openGallery() }
            profileName.setText(if (user?.displayName.isNullOrEmpty()) "User" else user?.displayName)
            buttonSave.setOnClickListener { updateProfile() }
            setImage(this)
        }
    }

    private fun setImage(binding: FragmentEditProfileBinding) {
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

    private fun updateProfile() {
        binding.loading.show()
        try {
            user?.updateProfile(userProfileChangeRequest {
                displayName = binding.profileName.text.toString()
                photoUri = uri ?: user?.photoUrl
                Log.d("cek", "uri $photoUri $uri")
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

    private fun openGallery() {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, RESULT_LOAD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == RESULT_LOAD_IMAGE) {
            uri = data?.data ?: return
            Log.d("cek", "uri result ${data.data}")
            Glide.with(binding.profileImage.context ?: return)
                .load(uri)
                .into(binding.profileImage)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
}