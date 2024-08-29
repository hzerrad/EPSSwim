package com.example.epsswim.data.model.requestBody.absences

import com.example.epsswim.data.model.app.swimmer.Swimmer

data class AbsencesVariables(
    val objects: List<Swimmer>,
    val levelid: String,
    val trainerid: String,
    val description: String
)
