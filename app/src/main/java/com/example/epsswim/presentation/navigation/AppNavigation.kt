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
import com.example.epsswim.presentation.ui.common.viewmodels.UserViewModel
import com.example.epsswim.presentation.ui.parent.screens.HomeScreen
import com.example.epsswim.presentation.ui.parent.viewmodels.ParentViewModel
import com.example.epsswim.presentation.ui.trainer.screens.AbsenceScreen
import com.example.epsswim.presentation.ui.trainer.screens.CompetitionsScreen
import com.example.epsswim.presentation.ui.trainer.screens.LevelScreen
import com.example.epsswim.presentation.ui.trainer.screens.TrainerProfile
import com.example.epsswim.presentation.ui.trainer.viewmodels.TrainerViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewmodel,
    modifier: Modifier,
    isTrainer: MutableState<Boolean?>
) {
    val sharedViewModel : SharedViewModel = hiltViewModel()
    val trainerViewmodel : TrainerViewModel = hiltViewModel()
    val userViewModel : UserViewModel = hiltViewModel()
    val parentViewModel: ParentViewModel = hiltViewModel()

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
                parentViewModel = parentViewModel
            )
        }
        composable<Screen.SwimmerProfile>{
            val detail = it.toRoute<Screen.SwimmerProfile>()

            SwimmerProfile(
                navController = navController,
                swimmerId = detail.id,
                isParent =detail.isParent,
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel,
                parentViewModel = parentViewModel
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
                trainerViewModel = trainerViewmodel
            )
        }
        composable<Screen.CompetitionScreen>{
            CompetitionsScreen(
                navController = navController,
            )
        }
        composable<Screen.LevelScreen>{
            val detail = it.toRoute<Screen.LevelScreen>()

            LevelScreen(
                navController = navController,
                trainerViewmodel=trainerViewmodel,
                levelName = detail.name,
                levelID = detail.id
            )
        }
        composable<Screen.TrainerProfile>{
            TrainerProfile(
                authViewModel=authViewModel,
                navController = navController,
                trainerViewModel = trainerViewmodel,
                userViewModel = userViewModel

            )
        }
    }

}