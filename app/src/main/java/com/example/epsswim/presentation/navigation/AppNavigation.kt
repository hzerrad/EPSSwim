package com.example.epsswim.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.epsswim.presentation.ui.common.componants.MyBottomBar
import com.example.epsswim.presentation.ui.common.screens.LoginScreen
import com.example.epsswim.presentation.ui.common.screens.ParticipationDetailsScreen
import com.example.epsswim.presentation.ui.common.screens.SplashScreen
import com.example.epsswim.presentation.ui.common.screens.SwimmerProfile
import com.example.epsswim.presentation.ui.parent.screens.HomeScreen
import com.example.epsswim.presentation.ui.trainer.screens.AbsenceScreen
import com.example.epsswim.presentation.ui.trainer.screens.CompetitionsScreen
import com.example.epsswim.presentation.ui.trainer.screens.LevelScreen
import com.example.epsswim.presentation.ui.trainer.screens.TrainerProfile
import com.example.epsswim.presentation.utils.Constants
@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash
    ){
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
        composable<Screen.ParticipationDetails>{
            AbsenceScreen(
                navController = navController,
            )
        }
        composable<Screen.ParticipationDetails>{
            CompetitionsScreen(
                navController = navController,
            )
        }
        composable<Screen.ParticipationDetails>{
            LevelScreen(
                navController = navController,
            )
        }
        composable<Screen.ParticipationDetails>{
            TrainerProfile(
                navController = navController,
            )
        }
    }

}