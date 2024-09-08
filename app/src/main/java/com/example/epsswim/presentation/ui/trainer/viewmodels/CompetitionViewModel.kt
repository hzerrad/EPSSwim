package com.example.epsswim.presentation.ui.trainer.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.competition.CompetitionResponse
import com.example.epsswim.data.model.app.levels.LevelsResponse
import com.example.epsswim.data.model.requestBody.competition.CompetitionData
import com.example.epsswim.data.model.requestBody.competition.CompetitionVariables
import com.example.epsswim.data.model.requestBody.competition.Query
import com.example.epsswim.data.repositories.CompetitionRepository
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
class CompetitionViewModel @Inject constructor(private val competitionRepository: CompetitionRepository) : ViewModel()  {
    private val _levelList = MutableStateFlow<LevelsResponse?>(null)
    val levelList: StateFlow<LevelsResponse?> = _levelList

    private val _competitionList = MutableStateFlow<CompetitionResponse?>(null)
    val competitionList: StateFlow<CompetitionResponse?> = _competitionList
    private val _isNotConnected = mutableStateOf(false)
    val isNotConnected : State<Boolean> = _isNotConnected

    fun getTrainerSwimmers(){
        viewModelScope.launch {
            competitionRepository.getTrainerSwimmers(Query(Queries.GET_TRAINER_SWIMMERS)).enqueue(object :
                Callback<LevelsResponse> {
                override fun onResponse(call: Call<LevelsResponse>, response: Response<LevelsResponse>) {
                    if (response.isSuccessful) {
                        _levelList.value = response.body()
                        _isNotConnected.value = false
                    } else {
                        Log.d("LevelsApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LevelsResponse>, t: Throwable) {
                    _isNotConnected.value = true
                    Log.d("LevelsApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun insertCompetition(competitionData: CompetitionData){
        viewModelScope.launch {
            competitionRepository.insertCompetition(Query(
                query = Queries.INSERT_COMPETITION,
                variables = CompetitionVariables(competitionData)

            )).enqueue(object :
                Callback<CompetitionResponse> {
                override fun onResponse(call: Call<CompetitionResponse>, response: Response<CompetitionResponse>) {
                    if (response.isSuccessful) {
                        getCompetitions()
                        Log.d("TAG", "onResponse: Data inserted ")
                    } else {
                        Log.d("LevelsApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CompetitionResponse>, t: Throwable) {
                    Log.d("LevelsApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun getCompetitions(){
        viewModelScope.launch {
            competitionRepository.getCompetitions(Query(
                query = Queries.GET_ALL_COMPETITION,

            )).enqueue(object :
                Callback<CompetitionResponse> {
                override fun onResponse(call: Call<CompetitionResponse>, response: Response<CompetitionResponse>) {
                    if (response.isSuccessful) {
                        _isNotConnected.value = false
                        _competitionList.value = response.body()
                    } else {
                        Log.d("LevelsApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CompetitionResponse>, t: Throwable) {
                    _isNotConnected.value = true
                    Log.d("LevelsApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun deleteCompetition(competitionid: String){
        viewModelScope.launch {
            competitionRepository.deleteCompetition(Query(
                query = Queries.DELETE_COMPETITION,
                variables = CompetitionVariables(competitionid=competitionid)

            )).enqueue(object :
                Callback<CompetitionResponse> {
                override fun onResponse(call: Call<CompetitionResponse>, response: Response<CompetitionResponse>) {
                    if (response.isSuccessful) {
                        getCompetitions()
                        Log.d("TAG", "onResponse: Data Deleted ")
                    } else {
                        Log.d("LevelsApi", "onResponse: failed delete data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CompetitionResponse>, t: Throwable) {
                    Log.d("LevelsApi", "onFailure: failed delete data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun updateCompetition(competitionData: CompetitionData){
        viewModelScope.launch {
            competitionRepository.updateCompetition(Query(
                query = Queries.UPDATE_COMPETITION,
                variables = CompetitionVariables(competitionData=competitionData)

            )).enqueue(object :
                Callback<CompetitionResponse> {
                override fun onResponse(call: Call<CompetitionResponse>, response: Response<CompetitionResponse>) {
                    if (response.isSuccessful) {
                        getCompetitions()
                        Log.d("TAG", "onResponse: Data updated ")
                    } else {
                        Log.d("LevelsApi", "onResponse: failed update data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CompetitionResponse>, t: Throwable) {
                    Log.d("LevelsApi", "onFailure: failed update data, check your internet connection ${t.message}")
                }
            })

        }
    }
}