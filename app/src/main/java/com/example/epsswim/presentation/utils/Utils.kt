package com.example.epsswim.presentation.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun getFullName(firstName: String, lastName: String): String = "$firstName $lastName"

fun makeCall(phoneNumber: String,context: Context) {
    try {
        val intent = Intent(Intent.ACTION_CALL)
        val phoneUri = Uri.parse("tel:$phoneNumber")
        intent.data = phoneUri

        val permission = Manifest.permission.CALL_PHONE

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
        }
    } catch (e: Exception) {
        Log.e("TAG", "makeCall: ${e.message}")
    }
}