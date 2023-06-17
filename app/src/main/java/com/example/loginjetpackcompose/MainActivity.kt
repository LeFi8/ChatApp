package com.example.loginjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.loginjetpackcompose.authentication.FirebaseManager
import com.example.loginjetpackcompose.presentation.LoggedIn
import com.example.loginjetpackcompose.presentation.Login
import com.example.loginjetpackcompose.presentation.ScreenState
import com.example.loginjetpackcompose.presentation.SignUp
import com.example.loginjetpackcompose.presentation.rememberFirebaseGoogleAuthLauncher
import com.example.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val authenticationService = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoggedIn = remember { mutableStateOf(false) }
            val screenState = remember { mutableStateOf(ScreenState.LOGIN) }
            var user by remember { mutableStateOf(Firebase.auth.currentUser) }
            val launcher = rememberFirebaseGoogleAuthLauncher(
                onAuthComplete = { result ->
                    user = result.user
                    isLoggedIn.value = true
                    screenState.value = ScreenState.LOGGED_IN
                    Toast.makeText(
                        this,
                        getString(R.string.google_login_successful),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onAuthError = {
                    user = null
                }
            )

            LoginJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackHandler(
                        enabled = (screenState.value == ScreenState.SIGNUP) &&
                                (screenState.value != ScreenState.LOGIN)
                    ) {
                        screenState.value = ScreenState.LOGIN
                    }

                    when (screenState.value) {
                        ScreenState.LOGIN -> {
                            Login(
                                onLoginClick = { email, password ->
                                    lifecycleScope.launch {
                                        isLoggedIn.value = authenticationService.login(
                                            email, password, this@MainActivity
                                        )
                                        if (isLoggedIn.value) screenState.value =
                                            ScreenState.LOGGED_IN
                                    }
                                },
                                onNoAccountClick = {
                                    screenState.value = ScreenState.SIGNUP
                                },
                                onGoogleLoginClick = {
                                    val googleSignInClient = GoogleSignIn.getClient(
                                        applicationContext,
                                        authenticationService.googleSignInOptionsBuilder(this)
                                    )
                                    launcher.launch(googleSignInClient.signInIntent)
                                }
                            )
                        }

                        ScreenState.SIGNUP -> {
                            SignUp(
                                onSignUpButtonClick = { email, password ->
                                    lifecycleScope.launch {
                                        val registrationSuccessful = authenticationService.register(
                                            email,
                                            password,
                                            this@MainActivity
                                        )
                                        if (registrationSuccessful)
                                            screenState.value = ScreenState.LOGIN
                                    }
                                }
                            )
                        }

                        ScreenState.LOGGED_IN -> {
                            LoggedIn(
                                onLogoutClick = {
                                    isLoggedIn.value = false
                                    authenticationService.logout(this)
                                    screenState.value = ScreenState.LOGIN
                                },
                                authenticationService.getUserMail()
                            )
                        }
                    }
                }
            }
        }
    }
}
