package com.example.epsswim.data.model.requestBody.competition


data class Query(
    val query: String,
    val variables: CompetitionVariables? = null

)
