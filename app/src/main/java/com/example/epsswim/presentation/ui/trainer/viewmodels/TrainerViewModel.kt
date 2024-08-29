package com.example.epsswim.presentation.ui.trainer.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.absences.AbsencesResponse
import com.example.epsswim.data.model.app.levels.LevelsResponse
import com.example.epsswim.data.model.app.pfp.PfpResponse
import com.example.epsswim.data.model.app.swimmer.Children
import com.example.epsswim.data.model.app.trainer.TrainerResponse
import com.example.epsswim.data.model.requestBody.absences.AbsencesVariables
import com.example.epsswim.data.model.requestBody.absences.SwimmerId
import com.example.epsswim.data.model.requestBody.level.LevelVariables
import com.example.epsswim.data.model.requestBody.pfp.trainer.TrainerPfpVariables
import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.repositories.TrainerRepository
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

class TrainerViewModel @Inject constructor(private val trainerRepository: TrainerRepository) : ViewModel()  {
    private val _levelList = MutableStateFlow<LevelsResponse?>(null)
    val levelList: StateFlow<LevelsResponse?> = _levelList

    private val _trainerInfo = MutableStateFlow<TrainerResponse?>(null)
    val trainerInfo: StateFlow<TrainerResponse?> = _trainerInfo
    private val _swimmerList = MutableStateFlow<Children?>(null)
    val swimmerList: StateFlow<Children?> = _swimmerList


    fun getTrainerLevels(){
        viewModelScope.launch {
            trainerRepository.getTrainerLevels(Query(Queries.GET_LEVELS)).enqueue(object : Callback<LevelsResponse> {
                override fun onResponse(call: Call<LevelsResponse>, response: Response<LevelsResponse>) {
                    if (response.isSuccessful) {
                        _levelList.value = response.body()
                    } else {
                        Log.d("LevelsApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LevelsResponse>, t: Throwable) {
                    Log.d("LevelsApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun getTrainerInfo(){
        viewModelScope.launch {
            trainerRepository.getTrainerInfo(Query(Queries.GET_TRAINER_BY_ID)).enqueue(object : Callback<TrainerResponse> {
                override fun onResponse(call: Call<TrainerResponse>, response: Response<TrainerResponse>) {
                    if (response.isSuccessful) {
                        _trainerInfo.value = response.body()
                    } else {
                        Log.d("TrainerInfoApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<TrainerResponse>, t: Throwable) {
                    Log.d("TrainerInfoApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })

        }
    }
    fun updateTrainerPfp(trainerid: String, pfpUrl: String){
        viewModelScope.launch {
            trainerRepository.updateTrainerPfp(
                com.example.epsswim.data.model.requestBody.pfp.trainer.Query(
                    query = Queries.UPLOAD_TRAINER_PHOTO_PROFILE,
                    variables = TrainerPfpVariables(
                        trainerid = trainerid,
                        pfpUrl = pfpUrl
                    )
                )
            ).enqueue(object : Callback<PfpResponse> {
                override fun onResponse(call: Call<PfpResponse>, response: Response<PfpResponse>) {
                    if (response.isSuccessful) {
                        Log.d("UpdatePicApi", "onResponse: success fetch data ${response.body()}")
                        _trainerInfo.value = null
                        getTrainerInfo()
                    } else {
                        Log.d("UpdatePicApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PfpResponse>, t: Throwable) {
                    Log.d("UpdatePicApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })
        }

    }
    fun getSwimmersByLevelId (levelid: String,absencedate: String?){
        viewModelScope.launch {
            trainerRepository.getSwimmersByLevel(
                com.example.epsswim.data.model.requestBody.level.Query(
                    Queries.GET_SWIMMERS_BY_LEVEL_ID,
                    variables = LevelVariables(levelid = levelid, absencedate = absencedate)
                )
            ).enqueue(object : Callback<Children> {
                override fun onResponse(call: Call<Children>, response: Response<Children>) {
                    if (response.isSuccessful) {
                        _swimmerList.value = response.body()
                    } else {
                        Log.d("TrainerApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Children>, t: Throwable) {
                    Log.d("TrainerApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })
        }
    }
    fun insertAbsencesAndNotes(
        isLoading : MutableState<Boolean>,
        isError : MutableState<Boolean>,
        objects : List<SwimmerId>,
        levelid: String,
        trainerid: String,
        description: String,
    ){
        viewModelScope.launch {
            trainerRepository.insertAbsencesAndNotes(
                com.example.epsswim.data.model.requestBody.absences.Query(
                    Queries.INSERT_ABSENCES_AND_NOTE,
                    variables = AbsencesVariables(
                        objects = objects,
                        levelid = levelid,
                        trainerid = trainerid,
                        description = description
                    )
                )
            ).enqueue(object : Callback<AbsencesResponse> {
                override fun onResponse(
                    call: Call<AbsencesResponse>,
                    response: Response<AbsencesResponse>
                ) {
                    if (response.isSuccessful) {
                        isLoading.value = false
                        isError.value = false
                    } else {
                        isLoading.value = false
                        isError.value = true
                        Log.d("TrainerApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<AbsencesResponse>, t: Throwable) {
                    Log.d(
                        "TrainerApi",
                        "onFailure: failed fetch data, check your internet connection ${t.message}"
                    )
                }
            })
        }
    }


}