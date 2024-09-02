package com.example.epsswim.presentation.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

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