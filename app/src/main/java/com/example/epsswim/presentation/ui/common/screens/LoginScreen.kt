package com.example.epsswim.presentation.ui.common.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.theme.MyPrimary

@Preview
@Composable
fun LoginScreen() {
    Surface (modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.logo_login) ,
                contentDescription = "Logo login Screen",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(256.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                    fontFamily = FontFamily(listOf(Font(R.font.cairo_extra_bold))),
                    fontSize = 32.sp,
                    color = MyPrimary
                    )) {
                    append(stringResource(R.string.eps))
                }
                    withStyle(style = SpanStyle(
                        fontFamily = FontFamily(listOf(Font(R.font.cairo_extra_bold))),
                        fontSize = 32.sp,
                        color = Color.Black
                    )) {
                        append(stringResource(R.string.welcome))
                    }

                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Text(
                text = stringResource(R.string.login),
                fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
                fontSize = 24.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}