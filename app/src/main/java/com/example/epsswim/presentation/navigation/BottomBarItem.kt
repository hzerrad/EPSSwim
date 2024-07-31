package com.example.epsswim.presentation.navigation

import com.example.epsswim.R

sealed class BottomBarItem(var name : String, var icon :Int ){
    object  Competitions : BottomBarItem("Competitions", R.drawable.competition_ic)
    object  Absences : BottomBarItem("Absences", R.drawable.abscence_filled_ic)
    object  Profile : BottomBarItem("Profile", R.drawable.profile_ic)
}