package com.example.epsswim.data.utils

import com.auth0.android.jwt.JWT

object Utils {
    fun getRoleFromToken(token: String): String = JWT(token).getClaim("https://hasura.io/jwt/claims").asObject(Map::class.java)
        ?.get("x-hasura-role")
        .toString()


}