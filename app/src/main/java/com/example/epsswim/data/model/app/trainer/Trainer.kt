package com.example.epsswim.data.model.app.trainer

import com.example.epsswim.data.model.app.swimmer.Level

data class Trainer(
    val birthday: String,
    val bloodtype: String,
    val firstname: String,
    val lastname: String,
    val levels: List<Level>,
    val phonenumber: String,
    val trainerAbsences: List<TrainerAbsence>,
    val trainerAbsences_aggregate: TrainerAbsencesAggregate,
    val trainerid: String,
    val pfpUrl : String
)