package com.example.epsswim.presentation.ui.parent.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.model.app.swimmer.Children
import com.example.epsswim.data.model.requestBody.swimmer.Query
import com.example.epsswim.data.repositories.ParentRepository
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
class ParentViewModel @Inject constructor(private val parentRepository: ParentRepository) : ViewModel()  {
    private val _swimmerList = MutableStateFlow<Children?>(null)
    val swimmerList: StateFlow<Children?> = _swimmerList

    init {
        viewModelScope.launch {
            parentRepository.getParentSwimmers(Query(Queries.GET_PARENT_SWIMMERS)).enqueue(object :
                Callback<Children> {
                override fun onResponse(call: Call<Children>, response: Response<Children>) {
                    if (response.isSuccessful) {
                        _swimmerList.value = response.body()
                    } else {
                        Log.d("ParentApi", "onResponse: failed fetch data ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Children>, t: Throwable) {
                    Log.d("ParentApi", "onFailure: failed fetch data, check your internet connection ${t.message}")
                }
            })
        }
    }
}