package com.example.epsswim.data.model.app.participation

import com.example.epsswim.data.model.app.competition.Competition
import com.example.epsswim.data.model.app.swimmer.Swimmer

data class Data(
    val competitions_by_pk: Competition,
    val swimmerevents: List<Swimmerevent>,
    val swimmers_by_pk: Swimmer
)