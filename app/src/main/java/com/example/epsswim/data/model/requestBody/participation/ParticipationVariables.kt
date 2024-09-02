package com.example.epsswim.data.model.requestBody.participation

import androidx.collection.LongList

data class ParticipationVariables(
    val competitionid: String,
    val swimmerid: String,
    val eventtypeid: String? = null,
    val laptimes: List<Long>? = null
)