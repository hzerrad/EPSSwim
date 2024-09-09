package com.example.epsswim.data.model.requestBody.participation

import androidx.collection.LongList

data class ParticipationVariables(
    val competitionid: String? = null,
    val swimmerid: String? = null,
    val eventtypeid: String? = null,
    val laptimes: List<Long>? = null,
    val swimmereventid : String? = null
)