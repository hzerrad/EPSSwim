package com.example.epsswim.data.model.requestBody.level

import com.example.epsswim.data.model.requestBody.level.LevelVariables

data class Query(
    val query: String,
    val variables: LevelVariables? = null

)
