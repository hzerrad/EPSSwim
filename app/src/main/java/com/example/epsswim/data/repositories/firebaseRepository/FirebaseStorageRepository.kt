package com.example.epsswim.data.repositories.firebaseRepository

import android.net.Uri
import com.example.epsswim.data.network.FirebaseStorageInterface
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseStorageRepository @Inject constructor(private val firebaseStorage: FirebaseStorage) :
    FirebaseStorageInterface {
    override suspend fun uploadImage(imageUri: Uri,filename: String): Result<String> {
        return try {
            val storageRef = firebaseStorage.reference.child("profile_pics/$filename")
            storageRef.putFile(imageUri).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            Result.success(downloadUrl)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}