package com.example.epsswim.data.model.requestBody.competition.updateCompetition

import com.example.epsswim.data.model.requestBody.competition.CompetitionData


data class Query(
    val query: String,
    val variables: CompetitionData? = null

)
