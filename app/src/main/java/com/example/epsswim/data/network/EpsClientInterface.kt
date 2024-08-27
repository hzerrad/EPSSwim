package com.example.epsswim.data.network

import com.example.epsswim.data.model.app.levels.LevelsResponse
import com.example.epsswim.data.model.app.swimmer.Children
import com.example.epsswim.data.model.app.trainer.TrainerResponse
import com.example.epsswim.data.model.requestBody.Query
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface EpsClientInterface {
    @POST("graphql/")
    fun getParentSwimmers(
        @Body query: Query
    ): Call<Children>

    @POST("graphql/")
    fun getSwimmerById(
        @Body query: Query
    ): Call<Children>


    @POST("graphql/")
    fun getLevels(
        @Body query: Query
    ): Call<LevelsResponse>

    @POST("graphql/")
    fun getTrainerInfo(
        @Body query: Query
    ): Call<TrainerResponse>
}