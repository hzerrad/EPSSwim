package com.example.epsswim.presentation.ui.common.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.viewmodels.AuthViewmodel
import com.example.epsswim.presentation.ui.theme.MyRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, authViewmodel: AuthViewmodel) {

    val isLoggedIn by authViewmodel.isLoggedIn.collectAsState()
    val role by authViewmodel.role.collectAsState()
    LaunchedEffect(key1 = isLoggedIn, key2 = role) {
        delay(500)
        isLoggedIn?.let {
            if (it){
                authViewmodel.getRole()
                when(role){
                    "coach" ->{
                        navController.navigate(Screen.AbsenceScreen)
                    }
                    "parent" ->{
                        navController.navigate(Screen.ParentHome)
                    }
                    else -> {}
                }
            }
            else{
                navController.navigate(Screen.Login)
            }
        }



    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.splash_bg) ,
            contentDescription = "Splash Screen bg",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight()
        )
        Image(
            painter = painterResource(id = R.drawable.logo_white) ,
            contentDescription = "Logo Splash Screen",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp)
                .align(Alignment.Center)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White
                )) {
                    append("By ")
                }
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MyRed
                )) {
                    append("MTC Company. ")
                }
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White
                )) {
                    append("All rights reserved.")
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 25.dp)
        )
    }
}