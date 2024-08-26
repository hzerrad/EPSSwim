package com.example.epsswim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.epsswim.presentation.ui.common.screens.LoginScreen
import com.example.epsswim.presentation.ui.common.screens.ParticipationDetailsScreen
import com.example.epsswim.presentation.ui.common.screens.SplashScreen
import com.example.epsswim.presentation.ui.common.screens.SwimmerProfile
import com.example.epsswim.presentation.ui.common.viewmodels.AuthViewmodel
import com.example.epsswim.presentation.ui.common.viewmodels.SharedViewModel
import com.example.epsswim.presentation.ui.parent.screens.HomeScreen
import com.example.epsswim.presentation.ui.trainer.screens.AbsenceScreen
import com.example.epsswim.presentation.ui.trainer.screens.CompetitionsScreen
import com.example.epsswim.presentation.ui.trainer.screens.LevelScreen
import com.example.epsswim.presentation.ui.trainer.screens.TrainerProfile

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier,
    isTrainer: MutableState<Boolean?>
) {
    val authViewModel : AuthViewmodel = hiltViewModel()
    val sharedViewModel : SharedViewModel = hiltViewModel()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Splash
    ){

        composable<Screen.Splash>{
            SplashScreen(
                authViewmodel = authViewModel,
                navController = navController
            )
        }
        composable<Screen.Login>{
            LoginScreen(
                authViewmodel = authViewModel,
                navController = navController,
                isTrainer = isTrainer
            )
        }
        composable<Screen.ParentHome>{
            HomeScreen(
                authViewModel = authViewModel,
                navController = navController,
            )
        }
        composable<Screen.SwimmerProfile>{
            val detail = it.toRoute<Screen.SwimmerProfile>()

            SwimmerProfile(
                navController = navController,
                swimmerId = detail.id,
                sharedViewModel = sharedViewModel
            )
        }
        composable<Screen.ParticipationDetails>{
            ParticipationDetailsScreen(
                navController = navController,
                isTrainer = isTrainer
            )
        }
        composable<Screen.AbsenceScreen>{
            AbsenceScreen(
                navController = navController,
            )
        }
        composable<Screen.CompetitionScreen>{
            CompetitionsScreen(
                navController = navController,
            )
        }
        composable<Screen.LevelScreen>{
            LevelScreen(
                navController = navController,
            )
        }
        composable<Screen.TrainerProfile>{
            TrainerProfile(
                authViewModel=authViewModel,
                navController = navController,
            )
        }
    }

}