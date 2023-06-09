package com.example.loginjetpackcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginjetpackcompose.R
import com.example.loginjetpackcompose.ui.theme.Ivory
import com.example.loginjetpackcompose.ui.theme.Poppins

@Preview
@Composable
fun SignUp() {
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
        Text(text = stringResource(id = R.string.your_mail))

        // TODO("add text fields and signup logic")
    }
}