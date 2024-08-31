package com.example.epsswim.data.model.requestBody.absences

import com.example.epsswim.data.model.app.swimmer.Swimmer

data class AbsencesVariables(
    val objects1: List<SwimmerId>,
    val objects2: List<String>,
    val absencedate : String,
    val levelid: String,
    val trainerid: String,
    val description: String

)
