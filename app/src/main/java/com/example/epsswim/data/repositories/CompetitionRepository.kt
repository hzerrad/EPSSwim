package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.requestBody.level.Query
import com.example.epsswim.data.network.EpsClientInterface
import javax.inject.Inject

class CompetitionRepository  @Inject constructor(private val epsClientInterface: EpsClientInterface)  {
 fun getTrainerSwimmers(query: Query) = epsClientInterface.getTrainerSwimmers(query)
}