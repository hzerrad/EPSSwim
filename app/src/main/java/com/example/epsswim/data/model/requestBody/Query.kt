package com.example.epsswim.data.model.requestBody

data class Query(
    val query: String,
    val variables: SwimmerVariables? = null

)
