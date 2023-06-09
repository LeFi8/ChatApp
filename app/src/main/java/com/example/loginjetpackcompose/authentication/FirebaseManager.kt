package com.example.loginjetpackcompose.authentication

import android.app.Activity
import android.widget.Toast
import com.example.loginjetpackcompose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseManager {

    private var auth: FirebaseAuth = Firebase.auth

    suspend fun login(email: String, password: String, activity: Activity): Boolean {
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
        auth.signOut()
    }

    suspend fun register(email: String, password: String, activity: Activity): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Toast.makeText(
                activity,
                activity.getString(R.string.account_created),
                Toast.LENGTH_SHORT
            ).show()
            true
        } catch (e: Exception) {
            val message = activity.getString(R.string.signup_failed, e.message)
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            false
        }
    }
}