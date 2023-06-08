package com.example.loginjetpackcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loginjetpackcompose.ui.theme.Ivory

@Composable
fun LoggedIn(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Ivory)
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You are logged in")
        Button(onClick = onLogoutClick) {
            Text(text = "Log out")
        }
    }
}