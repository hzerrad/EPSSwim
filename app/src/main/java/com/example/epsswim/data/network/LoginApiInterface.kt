package com.example.epsswim.data.network

import com.example.epsswim.data.model.auth.LoginBody
import com.example.epsswim.data.model.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiInterface {
    @POST("auth/login/")
    fun login(
       @Body loginBody: LoginBody
    ): Call<LoginResponse>
}