package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.participation.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class ParticipationRepository @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getSwimmingTypes(query: Query) = epsClientInterface.getSwimmingTypes(query)
    fun insertParticipation(query: Query) = epsClientInterface.insertParticipation(query)
    fun getParticipation(query: Query) = epsClientInterface.getParticipation(query)
    fun deleteParticipation(query: Query) = epsClientInterface.deleteParticipation(query)
}