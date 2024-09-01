package com.example.epsswim.data.model.requestBody.competition

data class CompetitionData(
    val competitiondate: String,
    val event: String,
    val isbrevet: Boolean,
    val location: String,
    val participants: Participants
)