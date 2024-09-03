package com.example.epsswim.data.network

import android.net.Uri
import javax.inject.Singleton

@Singleton
interface FirebaseStorageInterface {
    suspend fun uploadImage(imageUri: Uri,filename: String): Result<String>

}