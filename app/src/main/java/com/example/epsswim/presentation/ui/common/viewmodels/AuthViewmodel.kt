package com.example.epsswim.presentation.ui.common.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.epsswim.data.model.auth.LoginBody
import com.example.epsswim.data.model.auth.LoginResponse
import com.example.epsswim.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel  @Inject constructor(private val authRepo: AuthRepository) : ViewModel()  {
    val token = mutableStateOf<String?>(null)
    fun login(loginBody: LoginBody) {
        authRepo.login(loginBody).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    token.value = response.body()?.token
                    Log.d("AuthApi", "onResponse: ${response.body()}")
                } else {
                    Log.d("AuthApi", "onResponse: failed fetch data ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("AuthApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
            }
        })
    }
}