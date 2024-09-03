package com.example.epsswim.presentation.ui.common.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epsswim.data.repositories.firebaseRepository.FirebaseStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val firebaseStorageRepository: FirebaseStorageRepository) : ViewModel() {
    private val _uploadResult = MutableLiveData<Result<String>>()
    val uploadResult: LiveData<Result<String>> = _uploadResult

    fun uploadProfilePicture(imageUri: Uri,filename:String) {
        viewModelScope.launch {
            val result = firebaseStorageRepository.uploadImage(imageUri, filename = filename)
            _uploadResult.value = result
        }
    }
}