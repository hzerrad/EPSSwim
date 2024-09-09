package com.example.epsswim.data.model.app.participation

import com.example.epsswim.data.model.app.participation.swimmingtypes.Eventtype

data class Swimmerevent(
    val swimmereventid : String,
    val eventtype: Eventtype,
    val laptimes: List<Long>
)