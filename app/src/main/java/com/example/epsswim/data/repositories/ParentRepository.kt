package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class ParentRepository @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getParentSwimmers(query: Query) = epsClientInterface.getParentSwimmers(query)

}