package com.example.epsswim.data.model.app.swimmer

data class Swimmer(
    val birthday: String,
    val firstname: String,
    val ispro: Boolean,
    val lastname: String,
    val level: Level,
    val pfpUrl: String,
    val swimmerid: String,
    val trainer: Trainer,
    val swimmerAbsences_aggregate: SwimmerAbsencesAggregate,
    val totalAbsences: SwimmerAbsencesAggregate,
    val sex: String

)