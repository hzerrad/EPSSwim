package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class SharedRepository  @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getSwimmerById(query: Query) = epsClientInterface.getSwimmerById(query)
}