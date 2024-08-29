package com.example.epsswim.data.model.requestBody.level

data class Query(
    val query: String,
    val variables: LevelVariables? = null

)
