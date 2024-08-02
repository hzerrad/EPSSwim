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
    data object SwimmerProfile : Screen()

    @Serializable
    data object ParticipationDetails : Screen()
}