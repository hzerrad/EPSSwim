package com.example.epsswim.data.repositories.tokenRepository

interface JWTManager {
    suspend fun saveAccessJwt(token: String)
    suspend fun saveRefreshJwt(token: String)
    suspend fun getAccessJwt(): String?
    suspend fun getRefreshJwt(): String?
    suspend fun clearAllTokens()
    suspend fun getRole(): String?
}