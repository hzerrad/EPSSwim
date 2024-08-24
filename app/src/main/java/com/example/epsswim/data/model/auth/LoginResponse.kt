package com.example.epsswim.data.model.auth

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val user: User?

)
