package com.example.loginjetpackcompose.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginjetpackcompose.R
import com.example.loginjetpackcompose.ui.theme.EmperorDark
import com.example.loginjetpackcompose.ui.theme.Ivory
import com.example.loginjetpackcompose.ui.theme.Poppins
import com.example.loginjetpackcompose.ui.theme.Yellow

@Preview
@Composable
fun SignUp() {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Ivory)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.create_account
            ),
            fontSize = 36.sp,
            fontFamily = Poppins,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center
        )
        Divider(modifier = Modifier.padding(bottom = 24.dp))
        Text(
            text = stringResource(id = R.string.your_mail),
            modifier = Modifier.padding(start = 12.dp),
            fontFamily = Poppins
        )
        TextInput(inputType = InputType.Username) { user = it }

        Text(
            text = stringResource(id = R.string.your_password),
            modifier = Modifier.padding(start = 12.dp, top = 24.dp),
            fontFamily = Poppins
        )
        TextInput(inputType = InputType.Password) { password = it }

        Text(
            text = stringResource(id = R.string.repeat_password),
            modifier = Modifier.padding(start = 12.dp, top = 24.dp),
            fontFamily = Poppins
        )
        TextInput(inputType = InputType.Password) {
            if (it != password) passwordError = true
        }

        Divider(modifier = Modifier.padding(vertical = 32.dp))
        Button(
            onClick = {}, //TODO: validation
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Yellow)
        ) {
            Text(text = stringResource(id = R.string.signup), Modifier.padding(vertical = 8.dp))
        }

    }
}