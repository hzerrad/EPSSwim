package com.example.epsswim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.epsswim.presentation.ui.common.screens.LoginScreen
import com.example.epsswim.presentation.ui.common.screens.ParticipationDetailsScreen
import com.example.epsswim.presentation.ui.common.screens.SplashScreen
import com.example.epsswim.presentation.ui.common.screens.SwimmerProfile
import com.example.epsswim.presentation.ui.parent.screens.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash){
        composable<Screen.Splash>{
            SplashScreen(
                navController = navController
            )
        }
        composable<Screen.Login>{
            LoginScreen(
                navController = navController,
            )
        }
        composable<Screen.ParentHome>{
            HomeScreen(
                navController = navController,
            )
        }
        composable<Screen.SwimmerProfile>{
            SwimmerProfile(
                navController = navController,
            )
        }
        composable<Screen.ParticipationDetails>{
            ParticipationDetailsScreen(
                navController = navController,
            )
        }
    }
}