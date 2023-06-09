package com.example.loginjetpackcompose.authentication

import android.app.Activity
import android.widget.Toast
import com.example.loginjetpackcompose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Authentication {

    private lateinit var auth: FirebaseAuth

    suspend fun login(email: String, password: String, activity: Activity): Boolean {
        auth = Firebase.auth

        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            if (authResult.user == null) throw Exception()

            val user = auth.currentUser
            Toast.makeText(activity, user?.email, Toast.LENGTH_SHORT).show()
            true
        } catch (e: Exception) {
            val message = activity.getString(R.string.login_failed, e.message)
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            false
        }
    }


    fun logout() {
        Firebase.auth.signOut()
    }
}