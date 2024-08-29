package com.example.epsswim.presentation.navigation

import androidx.compose.ui.res.stringResource
import com.example.epsswim.R
import com.example.epsswim.presentation.utils.Constants

sealed class BottomBarItem(
    var titleId : Int,
    var icon :Int,
    var iconFilled : Int,
    var route : String ,
    var screen : Screen
){
    object  Competitions : BottomBarItem(
        titleId =  R.string.the_competitions,
        icon = R.drawable.competition_ic,
        iconFilled = R.drawable.competition_filled_ic,
        route = Constants.PACKAGE_NAME + "CompetitionScreen",
        screen = Screen.CompetitionScreen
    )
    object  Absences : BottomBarItem(
        titleId =  R.string.absences,
        R.drawable.abscence_ic,
        iconFilled = R.drawable.abscence_filled_ic,
        route = Constants.PACKAGE_NAME + "AbsenceScreen",
        screen = Screen.AbsenceScreen
    )
    object  Profile : BottomBarItem(
        titleId =  R.string.the_profile,
        R.drawable.profile_ic,
        iconFilled = R.drawable.profile_filled_ic,
        route = Constants.PACKAGE_NAME + "TrainerProfile",
        screen = Screen.TrainerProfile
    )
}