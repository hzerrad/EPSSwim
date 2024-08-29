package com.example.epsswim.data.model.requestBody.absences

data class Query(
    val query: String,
    val variables: AbsencesVariables? = null

)
