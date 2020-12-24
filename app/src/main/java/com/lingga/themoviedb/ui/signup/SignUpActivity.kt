package com.lingga.themoviedb.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.lingga.themoviedb.MyApplication
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.ActivitySignUpBinding
import com.lingga.themoviedb.ui.base.BaseActivity
import com.lingga.themoviedb.ui.login.LoginActivity
import com.lingga.themoviedb.utils.ext.*
import com.lingga.themoviedb.utils.ui.CustomDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val auth by lazy { FirebaseAuth.getInstance() }

    private val customDialog by lazy { CustomDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) setTransparentStatusBar()
        else setTransparentStatusBarBlack()
        binding.apply {
            buttonSignUp.setOnClickListener { proceedAccount() }
            backButton.setOnClickListener { onBackPressed() }
        }
    }

    private fun signUp() {
        binding.loading.show()
        try {
            auth.createUserWithEmailAndPassword(
                binding.emailField.text.toString(),
                binding.passwordField.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    sendVerificationEmail()
                    customDialog.showSuccessCreateAccount(this, { navigateToLogin() })
                    binding.loading.hide()
                } else {
                    binding.loading.hide()
                }
            }
        } catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun sendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful) {
                auth.signOut()
                Log.d(TAG, "susksesverif")
            } else {
                Log.d(TAG, "gagalverif")
            }
        }
    }

    private fun proceedAccount() {
        binding.apply {
            if (passwordField.checkIsNotEmpty() && confirmPasswordField.checkIsNotEmpty() && emailField.checkIsNotEmpty()) {
                when {
                    passwordField.text.length < 8 && confirmPasswordField.text.length < 8 -> {
                        passwordField.error = getString(R.string.notice_password_less_8_characters)
                        confirmPasswordField.error =
                            getString(R.string.notice_password_less_8_characters)
                        Toast.makeText(
                            this@SignUpActivity,
                            getString(R.string.notice_password_less_8_characters),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    passwordField.text.toString() != confirmPasswordField.text.toString() -> {
                        confirmPasswordField.error = getString(R.string.password_doesnt_match)
                        Toast.makeText(
                            this@SignUpActivity,
                            getString(R.string.password_doesnt_match),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(emailField.text.toString()).matches() -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            getString(R.string.input_valid_email),
                            Toast.LENGTH_SHORT
                        ).show()
                        emailField.error = getString(R.string.input_valid_email)
                    }
                    else -> {
                        signUp()
                    }
                }
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    getString(R.string.fill_field),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}