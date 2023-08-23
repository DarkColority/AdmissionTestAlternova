package com.example.admissiontest.api.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUpWithEmailAndPassword(email: String, password: String, callback: (Boolean, String) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "User registered successfully")
                } else {
                    callback(false, task.exception?.message ?: "Registration failed")
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, password: String, callback: (Boolean, String) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Login successful")
                } else {
                    callback(false, task.exception?.message ?: "Login failed")
                }
            }
    }
}


