package com.example.epsswim.presentation.ui.common.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.participation.ParticipationResponse
import com.example.epsswim.data.model.app.participation.swimmingtypes.SwimmingTypesResponse
import com.example.epsswim.data.model.requestBody.participation.ParticipationVariables
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

    private val _participation = MutableStateFlow<ParticipationResponse?>(null)
    val participation: StateFlow<ParticipationResponse?> = _participation

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
    fun getParticipation(swimmerID:String,competitionID :String){
        viewModelScope.launch {
            participationRepository.getParticipation(Query(
                query = Queries.GET_PARTICIPATION,
                variables = ParticipationVariables(competitionid = competitionID, swimmerid = swimmerID)
            )).enqueue(object :
                Callback<ParticipationResponse> {
                override fun onResponse(call: Call<ParticipationResponse>, response: Response<ParticipationResponse>) {
                    if (response.isSuccessful) {
                        _participation.value = response.body()
                        Log.d("Participation", "onResponse: data : ${response.body()}")

                    } else {
                        Log.d("Participation", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ParticipationResponse>, t: Throwable) {
                    Log.d("Participation", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun insertParticipation(swimmerID:String,competitionID :String){
        viewModelScope.launch {
            participationRepository.insertParticipation(Query(
                query = Queries.INSERT_PARTICIPATION,
                variables = ParticipationVariables(competitionid = competitionID, swimmerid = swimmerID)
            )).enqueue(object :
                Callback<ParticipationResponse> {
                override fun onResponse(call: Call<ParticipationResponse>, response: Response<ParticipationResponse>) {
                    if (response.isSuccessful) {
                        Log.d("Participation", "onResponse: data : ${response.body()}")

                    } else {
                        Log.d("Participation", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ParticipationResponse>, t: Throwable) {
                    Log.d("Participation", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }

}