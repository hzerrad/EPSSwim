package com.example.epsswim.data.network

import com.example.epsswim.data.model.app.Children
import com.example.epsswim.data.model.app.Swimmer
import com.example.epsswim.data.model.auth.LoginBody
import com.example.epsswim.data.model.auth.LoginResponse
import com.example.epsswim.data.model.requestBody.Query
import com.example.epsswim.data.model.requestBody.SwimmerVariables
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
}