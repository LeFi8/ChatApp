package com.example.loginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.loginjetpackcompose.authentication.FirebaseManager
import com.example.loginjetpackcompose.presentation.LoggedIn
import com.example.loginjetpackcompose.presentation.Login
import com.example.loginjetpackcompose.presentation.ScreenState
import com.example.loginjetpackcompose.presentation.SignUp
import com.example.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val authenticationService = FirebaseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoggedIn = remember { mutableStateOf(false) }
            val screenState = remember { mutableStateOf(ScreenState.LOGIN) }

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
                            LoggedIn(onLogoutClick = {
                                isLoggedIn.value = false
                                authenticationService.logout()
                                screenState.value = ScreenState.LOGIN
                            })
                        }
                    }
                }
            }
        }
    }
}
