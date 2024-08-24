package com.example.epsswim.data.network

import android.content.Context
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor (private val context: Context) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

//        val token = runBlocking {
//            UserPreferences.getAccessToken(context).firstOrNull()
//        }
//
//        // Add the token to the request if it exists
//        token?.let {
//            requestBuilder.addHeader("Authorization", "Bearer $it")
//        }

        return chain.proceed(requestBuilder.build())
    }

}