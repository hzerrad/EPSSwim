package com.example.epsswim.presentation.ui.common.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.swimmer.Children
import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.model.requestBody.swimmer.SwimmerVariables
import com.example.epsswim.data.repositories.SharedRepository
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
class SharedViewModel @Inject constructor(private val sharedRepository: SharedRepository) : ViewModel()  {
    private val _swimmer = MutableStateFlow<Children?>(null)
    val swimmer: StateFlow<Children?> = _swimmer

    fun getSwimmer(swimmerId: String) {
        viewModelScope.launch {
            sharedRepository.getSwimmerById(
                Query(query = Queries.GET_SWIMMER_BY_ID, variables = SwimmerVariables(swimmerId))
            ).enqueue(object :
                Callback<Children> {
                override fun onResponse(call: Call<Children>, response: Response<Children>) {
                    if (response.isSuccessful) {
                        _swimmer.value = response.body()
                    } else {
                        Log.d("SwimmerApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Children>, t: Throwable) {
                    Log.d("SwimmerApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })
        }
    }
}