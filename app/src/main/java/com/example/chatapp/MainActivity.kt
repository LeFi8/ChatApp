package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chatapp.ui.theme.ChatAppTheme
import com.example.chatapp.ui.theme.EmperorDark
import com.example.chatapp.ui.theme.Ivory
import com.example.chatapp.ui.theme.Poppins
import com.example.chatapp.ui.theme.Yellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun Login() {
        Column(modifier = Modifier
            .background(Ivory)
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.app_name), fontSize = 48.sp, fontFamily = Poppins)
            LottieIcon(animationRes = R.raw.chat_lottie)
            TextInput(inputType = InputType.Username)
            TextInput(inputType = InputType.Password)
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Yellow)
            ) {
                Text(text = stringResource(id = R.string.login), Modifier.padding(vertical = 8.dp))
            }
            Divider(
                color = EmperorDark,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 48.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.dont_have_account), color = EmperorDark)
                TextButton(onClick = {}) {
                    Text(text = stringResource(id = R.string.signup), color = Yellow)
                }
            }
        }
    }

    @Composable
    fun LottieIcon(animationRes: Int,
                   iconSize: Dp = 192.dp,
                   repeatCount: Int = LottieConstants.IterateForever
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(animationRes)
        )

        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = repeatCount,
            restartOnPlay = true
        )
        LottieAnimation(composition = composition, progress = progress, Modifier.size(iconSize))
    }

    sealed class InputType (
        val labelId: Int,
        val icon: ImageVector,
        val keyboardOptions: KeyboardOptions,
        val visualTransformation: VisualTransformation
        ) {
        object Username : InputType(
            labelId = R.string.username_mail,
            icon = Icons.Default.Person,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            visualTransformation = VisualTransformation.None
        )

        object Password : InputType(
            labelId = R.string.password,
            icon = Icons.Default.Lock,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextInput(inputType: InputType) {
        var value by remember { mutableStateOf("") }
        TextField(
            value = value,
            onValueChange = {value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(inputType.icon, contentDescription = "Icon") },
            label = { Text(text = stringResource(id = inputType.labelId)) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            keyboardOptions = inputType.keyboardOptions,
            visualTransformation = inputType.visualTransformation
        )
    }
}
