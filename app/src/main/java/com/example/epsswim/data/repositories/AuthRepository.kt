package com.example.epsswim.data.repositories

import com.example.epsswim.data.model.auth.LoginBody
import com.example.epsswim.data.network.LoginApiInterface
import javax.inject.Inject

class AuthRepository  @Inject constructor(private val apiInterface: LoginApiInterface) {
    fun login(loginBody: LoginBody) = apiInterface.login(loginBody)

}