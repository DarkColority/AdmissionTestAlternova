package com.example.admissiontest.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.admissiontest.R
import com.example.admissiontest.api.repository.AuthRepository
import com.example.admissiontest.databinding.ActivityAuthBinding
import com.example.admissiontest.ui.productlist.ProductListActivity

class AuthActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions,
    SignUpFragment.SignUpFragmentActions {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var binding: ActivityAuthBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        authViewModel = ViewModelProvider(this, AuthViewModelFactory(
            AuthRepository(),
            sharedPreferences)
        ).get(AuthViewModel::class.java)

        if(authViewModel.isLoggedIn()){
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, ProductListActivity::class.java))
        finish()
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment)
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
    }

    override fun onLoginFieldsValidated(email: String, password: String) {
        authViewModel.login(email, password) { isSuccess, message ->
            if (isSuccess) {
                Toast.makeText(this, R.string.login, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProductListActivity::class.java))
                finish()
            } else {
                showErrorDialog(R.string.there_was_an_error)
            }
        }
    }

    override fun onSignUpFieldsValidated(
        email: String,
        password: String
    ) {
        authViewModel.signUp(email, password) { isSuccess, message ->
            if (isSuccess) {
                Toast.makeText(this, R.string.login, Toast.LENGTH_SHORT).show()
                startMainActivity()

            } else {
                showErrorDialog(R.string.there_was_an_error)
            }
        }
    }

    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(this)
            .setTitle(R.string.there_was_an_error)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dismiss dialog **/ }
            .create()
            .show()
    }
}