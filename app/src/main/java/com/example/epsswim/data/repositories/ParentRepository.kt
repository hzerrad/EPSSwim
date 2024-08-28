package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class ParentRepository @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getParentSwimmers(query: Query) = epsClientInterface.getParentSwimmers(query)
    fun updateSwimmerPfp(query: com.example.epsswim.data.model.requestBody.pfp.swimmer.Query) = epsClientInterface.updateSwimmerPfp(query)

}