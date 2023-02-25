package com.example.mymovieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mymovieapp.R
import com.example.mymovieapp.data.User
import com.example.mymovieapp.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel : MainActivityViewModel = MainActivityViewModel()

    private val singleInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { resultCallback ->
        this.onSignInResult(resultCallback)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
    }

    private fun openRegistrationScreen() {
        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        singleInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val authUser = FirebaseAuth.getInstance().currentUser
                authUser?.let {
                    val email = it.email.toString()
                    val uid = it.uid
                    val firebaseUser = User()

                    mMainActivityViewModel.updateUserData(firebaseUser, uid)

                    val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                    startActivity(intentToAnotherScreen)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()
            }
            else -> {

            }
        }
    }
}