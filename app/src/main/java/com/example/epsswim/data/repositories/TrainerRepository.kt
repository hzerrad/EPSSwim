package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class TrainerRepository @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getTrainerLevels(query: Query) = epsClientInterface.getLevels(query = query)
    fun getTrainerInfo(query: Query) = epsClientInterface.getTrainerInfo(query = query)
    fun updateTrainerPfp(query: com.example.epsswim.data.model.requestBody.pfp.trainer.Query) = epsClientInterface.updateTrainerPfp(query = query)
    fun getSwimmersByLevel(query: com.example.epsswim.data.model.requestBody.competition.Query) = epsClientInterface.getSwimmersByLevel(query)
    fun insertAbsencesAndNotes(query: com.example.epsswim.data.model.requestBody.absences.Query) = epsClientInterface.insertAbsencesAndNotes(query)
}