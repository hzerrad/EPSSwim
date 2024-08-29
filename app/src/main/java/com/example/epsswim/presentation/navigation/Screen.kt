package com.example.epsswim.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Screen {
    @Serializable
    data object ParentHome : Screen()

    @Serializable
    data object Splash : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data class SwimmerProfile(val id: String="",val isParent : Boolean) : Screen()

    @Serializable
    data object ParticipationDetails : Screen()

    @Serializable
    data object AbsenceScreen : Screen()

    @Serializable
    data object CompetitionScreen : Screen()

    @Serializable
    data class LevelScreen(val id: String,val name:String) : Screen()

    @Serializable
    data object TrainerProfile : Screen()
}