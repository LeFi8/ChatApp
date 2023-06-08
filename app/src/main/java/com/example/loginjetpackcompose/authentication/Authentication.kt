package com.example.loginjetpackcompose.authentication

import android.app.Activity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Authentication {

    private lateinit var auth: FirebaseAuth

    suspend fun login(email: String, password: String, activity: Activity): Boolean {
        auth = Firebase.auth

        val authSuccess: Boolean = try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            if (authResult.user != null) {
                val user = auth.currentUser
                Toast.makeText(activity, user?.email, Toast.LENGTH_SHORT).show()
                true
            } else {
                Toast.makeText(activity, "Login failed", Toast.LENGTH_SHORT).show()
                false
            }
        } catch (e: Exception) {
            Toast.makeText(activity, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            false
        }

        return authSuccess
    }

    fun logout() {
        Firebase.auth.signOut()
    }
}