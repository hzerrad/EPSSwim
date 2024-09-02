package com.example.epsswim.data.model.app.participation

import com.example.epsswim.data.model.app.participation.swimmingtypes.Eventtype

data class Swimmerevent(
    val eventtype: Eventtype,
    val laptimes: List<Double>
)