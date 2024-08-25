package com.example.epsswim.data.network

import android.content.Context
import com.example.epsswim.data.repositories.tokenRepository.JwtTokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor (
    private val jwtTokenDataStore: JwtTokenDataStore
) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking {
            jwtTokenDataStore.getAccessJwt()
        }
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

}