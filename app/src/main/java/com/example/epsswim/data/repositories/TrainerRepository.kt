package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class TrainerRepository @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getTrainerLevels(query: Query) = epsClientInterface.getLevels(query = query)
}