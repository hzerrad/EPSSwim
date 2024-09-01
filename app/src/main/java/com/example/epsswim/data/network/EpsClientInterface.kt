package com.example.epsswim.data.network

import com.example.epsswim.data.model.app.absences.AbsencesResponse
import com.example.epsswim.data.model.app.levels.LevelsResponse
import com.example.epsswim.data.model.app.pfp.PfpResponse
import com.example.epsswim.data.model.app.swimmer.Children
import com.example.epsswim.data.model.app.trainer.TrainerResponse
import com.example.epsswim.data.model.requestBody.swimmer.Query
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

    @POST("graphql/")
    fun updateTrainerPfp(
        @Body query: com.example.epsswim.data.model.requestBody.pfp.trainer.Query
    ): Call<PfpResponse>

    @POST("graphql/")
    fun updateSwimmerPfp(
        @Body query: com.example.epsswim.data.model.requestBody.pfp.swimmer.Query
    ): Call<PfpResponse>

    @POST("graphql/")
    fun getSwimmersByLevel(
        @Body query: com.example.epsswim.data.model.requestBody.level.Query
    ): Call<Children>
    @POST("graphql/")
    fun insertAbsencesAndNotes(
        @Body query: com.example.epsswim.data.model.requestBody.absences.Query
    ): Call<AbsencesResponse>

    @POST("graphql/")
    fun insertCompetition(
        @Body query: com.example.epsswim.data.model.requestBody.absences.Query
    ): Call<AbsencesResponse>

    @POST("graphql/")
    fun getCompetitions(
        @Body query: com.example.epsswim.data.model.requestBody.absences.Query
    ): Call<AbsencesResponse>

    @POST("graphql/")
    fun getTrainerSwimmers(
        @Body query: com.example.epsswim.data.model.requestBody.level.Query
    ): Call<LevelsResponse>

}