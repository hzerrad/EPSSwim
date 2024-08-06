package com.example.epsswim.presentation.ui.common.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.epsswim.presentation.ui.common.componants.MyBottomBar

@Preview
@Composable
fun Try (){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            MyBottomBar(navController = navController )
        }
    ) {
        Surface (modifier = Modifier.padding(it)){

        }
    }
}