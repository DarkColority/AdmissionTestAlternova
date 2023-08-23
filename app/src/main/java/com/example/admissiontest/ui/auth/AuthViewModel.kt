package com.example.admissiontest.ui.auth

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.admissiontest.api.repository.AuthRepository


class AuthViewModel(private val authRepository: AuthRepository,
                    private val sharedPreferences: SharedPreferences
                    ) : ViewModel() {

    private val IS_LOGGED_IN_KEY = "is_logged_in"


    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false)
    }

    fun signUp(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authRepository.signUpWithEmailAndPassword(email, password, callback)
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, true).apply()
    }

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        authRepository.signInWithEmailAndPassword(email, password, callback)
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, true).apply()
    }
}