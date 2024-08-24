package com.example.epsswim.data.model

data class LoginResponse(
    val success: Boolean,
    val token: String?,
    val user: User

)
