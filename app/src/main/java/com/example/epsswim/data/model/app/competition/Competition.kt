package com.example.epsswim.data.model.app.competition

data class Competition(
    val competitiondate: String,
    val competitionid: String,
    val event: String,
    val isbrevet: Boolean,
    val location: String,
    val participants: List<Participant>
)