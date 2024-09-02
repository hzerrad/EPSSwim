package com.example.epsswim.data.model.app.swimmer.profile

import com.example.epsswim.data.model.app.swimmer.Level
import com.example.epsswim.data.model.app.swimmer.SwimmerAbsencesAggregate
import com.example.epsswim.data.model.app.swimmer.Trainer

data class SwimmersByPk(
    val birthday: String,
    val firstname: String,
    val ispro: Boolean,
    val lastname: String,
    val level: Level,
    val pfpUrl: String,
    val sex: String,
    val swimmerAbsences: List<SwimmerAbsence>,
    val swimmerAbsences_aggregate: SwimmerAbsencesAggregate,
    val swimmerid: String,
    val trainer: Trainer
)