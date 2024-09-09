package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.competition.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class CompetitionRepository  @Inject constructor(private val epsClientInterface: EpsClientInterface)  {
 fun getTrainerSwimmers(query: Query) = epsClientInterface.getTrainerSwimmers(query)
 fun insertCompetition(query: Query) = epsClientInterface.insertCompetition(query)
 fun getCompetitions(query: Query) = epsClientInterface.getCompetitions(query)
 fun deleteCompetition(query: Query) = epsClientInterface.deleteCompetition(query)
 fun updateCompetition(query: com.example.epsswim.data.model.requestBody.competition.updateCompetition.Query) = epsClientInterface.updateCompetition(query)

}