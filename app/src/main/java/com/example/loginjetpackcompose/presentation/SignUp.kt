package com.example.loginjetpackcompose.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

        Button(
            onClick = {}, //TODO: validation
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Yellow)
        ) {
            Text(text = stringResource(id = R.string.signup), Modifier.padding(vertical = 8.dp))
        }

        DividerWithText(stringResource(id = R.string.or))

        // TODO: add sign up with google button
        /*Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            
        }
*/
    }
}

@Composable
fun DividerWithText(text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.weight(1f))
        Text(
            text = text,
            color = EmperorDark,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Divider(modifier = Modifier.weight(1f))
    }
}