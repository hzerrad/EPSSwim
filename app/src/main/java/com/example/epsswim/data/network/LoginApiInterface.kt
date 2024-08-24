package com.example.epsswim.data.network

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiInterface {
    @POST("")
    suspend fun login(
        @Query ("username") username: String,
    ): Call<String>
}