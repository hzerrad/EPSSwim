package com.example.epsswim.presentation.ui.common.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.participation.swimmingtypes.SwimmingTypesResponse
import com.example.epsswim.data.model.requestBody.participation.Query
import com.example.epsswim.data.repositories.ParticipationRepository
import com.example.epsswim.data.utils.Queries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ParticipationViewModel @Inject constructor(private val participationRepository: ParticipationRepository) : ViewModel()  {
    private val _swimmingTypes = MutableStateFlow<SwimmingTypesResponse?>(null)
    val swimmingTypes: StateFlow<SwimmingTypesResponse?> = _swimmingTypes

    fun getSwimmingTypes(){
        viewModelScope.launch {
            participationRepository.getSwimmingTypes(Query(Queries.GET_SWIMMING_TYPES)).enqueue(object :
                Callback<SwimmingTypesResponse> {
                override fun onResponse(call: Call<SwimmingTypesResponse>, response: Response<SwimmingTypesResponse>) {
                    if (response.isSuccessful) {
                        _swimmingTypes.value = response.body()
                        Log.d("SwimmingTypes", "onResponse: data : ${response.body()}")

                    } else {
                        Log.d("SwimmingTypes", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<SwimmingTypesResponse>, t: Throwable) {
                    Log.d("SwimmingTypes", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }

}