package com.example.epsswim.data.model.requestBody.competition

import com.example.epsswim.data.model.app.swimmer.Swimmer

data class CompetitionData(
    val competitionid :String? = null,
    val competitiondate: String,
    val event: String,
    val isbrevet: Boolean,
    val location: String,
    val participants: Participants? = null,
    val objects : List<CompetitionParticipant>? = null,
    val levelid : String
)