package com.example.epsswim.presentation.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun calculateAge(dateString: String): Int {
    // Define the date format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Parse the string to a LocalDate
    val birthDate = LocalDate.parse(dateString, formatter)

    // Get the current date
    val currentDate = LocalDate.now()

    // Calculate the period between the birth date and the current date
    val age = Period.between(birthDate, currentDate).years

    return age
}
fun getDate(localDate: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return localDate.format(formatter)

}
fun formatDate(value: String): String {
    val parts = value.split("/")
    return "${parts[2]}-${parts[1]}-${parts[0]}"
}
fun formatTime(timeMillis: Long): String {
    val localDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timeMillis),
        ZoneId.systemDefault()
    )
    val formatter = DateTimeFormatter.ofPattern(
        "mm:ss:SSS",
        Locale.getDefault()
    )
    return localDateTime.format(formatter)
}
fun parseTimeToMillis(timeString: String): Long {
    val parts = timeString.split(":")
    val minutes = parts[0].toLong()
    val seconds = parts[1].toLong()
    val milliseconds = parts[2].toLong()

    return (minutes * 60 * 1000) + (seconds * 1000) + milliseconds
}