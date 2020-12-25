package com.lingga.themoviedb.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lingga.themoviedb.MainActivity
import com.lingga.themoviedb.R
import com.lingga.themoviedb.databinding.ActivityLoginBinding
import com.lingga.themoviedb.ui.base.BaseActivity
import com.lingga.themoviedb.ui.signup.SignUpActivity
import com.lingga.themoviedb.utils.ext.hide
import com.lingga.themoviedb.utils.ext.setTransparentStatusBar
import com.lingga.themoviedb.utils.ext.setTransparentStatusBarBlack
import com.lingga.themoviedb.utils.ext.show
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    companion object {
        const val RC_SIGN_IN: Int = 1
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) setTransparentStatusBar()
        else setTransparentStatusBarBlack()
        setGso()
        button_google_login.setOnClickListener { signIn() }
        sign_up.setOnClickListener { navigateToSignUp() }
        button_login.setOnClickListener { signInWithEmail() }
    }

    private fun setGso() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions).apply {
            signOut()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Google sign in failed:", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signInWithEmail() {
        loading.show()
        try {
            firebaseAuth.signInWithEmailAndPassword(
                email_field.text.toString(),
                password_field.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    checkEmailIsVerified()
                    loading.hide()
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    loading.hide()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun checkEmailIsVerified() {
        if (firebaseAuth.currentUser?.isEmailVerified == true) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            firebaseAuth.signOut()
            Toast.makeText(this, getString(R.string.email_not_verified), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                loading.show()
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {
                loading.hide()
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}