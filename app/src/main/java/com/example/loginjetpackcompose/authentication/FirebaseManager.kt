package com.example.loginjetpackcompose.authentication

import android.app.Activity
import android.widget.Toast
import com.example.loginjetpackcompose.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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

    //TODO: finish
    suspend fun googleLogin(activity: Activity): Boolean {
        val oneTapClient = Identity.getSignInClient(activity);
        val signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(activity.getString(R.string.default_web_client_id))
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(true)
                .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

        val googleCredential = oneTapClient.getSignInCredentialFromIntent(activity.intent)
        val idToken = googleCredential.googleIdToken

        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            auth.signInWithCredential(firebaseCredential).await()
            Toast.makeText(activity, "Google login successful", Toast.LENGTH_SHORT).show()
            true
        } catch (e : Exception) {
            Toast.makeText(activity, "Google login failed", Toast.LENGTH_SHORT).show()
            false
        }
    }

}