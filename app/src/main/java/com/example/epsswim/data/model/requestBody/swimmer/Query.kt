package com.example.epsswim.data.model.requestBody.swimmer

data class Query(
    val query: String,
    val variables: SwimmerVariables? = null

)
