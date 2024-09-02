package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.app.swimmer.profile.SwimmerInfoResponse
import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.network.EpsClientInterface
import retrofit2.Call
import javax.inject.Inject

class SharedRepository  @Inject constructor(private val epsClientInterface: EpsClientInterface) {
    fun getSwimmerById(query: Query): Call<SwimmerInfoResponse> = epsClientInterface.getSwimmerById(query)
}