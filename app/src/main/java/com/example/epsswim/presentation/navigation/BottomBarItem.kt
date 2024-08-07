package com.example.epsswim.presentation.navigation

import com.example.epsswim.R
import com.example.epsswim.presentation.utils.Constants

sealed class BottomBarItem(
    var icon :Int,
    var iconFilled : Int,
    var route : String ,
    var screen : Screen
){
    object  Competitions : BottomBarItem(
        icon = R.drawable.competition_ic,
        iconFilled = R.drawable.competition_filled_ic,
        route = Constants.PACKAGE_NAME + "CompetitionScreen",
        screen = Screen.CompetitionScreen
    )
    object  Absences : BottomBarItem(
        R.drawable.abscence_ic,
        iconFilled = R.drawable.abscence_filled_ic,
        route = Constants.PACKAGE_NAME + "AbsenceScreen",
        screen = Screen.AbsenceScreen
    )
    object  Profile : BottomBarItem(
        R.drawable.profile_ic,
        iconFilled = R.drawable.profile_filled_ic,
        route = Constants.PACKAGE_NAME + "TrainerProfile",
        screen = Screen.TrainerProfile
    )
}