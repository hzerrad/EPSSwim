package com.example.epsswim.presentation.utils

import com.kizitonwose.calendar.core.WeekDay
import java.time.LocalDate

fun getArabicDate(date: LocalDate): String {
    val weekDay = date.dayOfWeek.name
    val dateParts = date.toString().split("-")
    val year = dateParts[0]
    val month = dateParts[1]
    val day = dateParts[2]

    val arabicWeekDay = getArabicWeekDay(weekDay)
    val arabicMonth = when (month) {
        "01" -> "جانفي"
        "02" -> "فيفري"
        "03" -> "مارس"
        "04" -> "أفريل"
        "05" -> "ماي"
        "06" -> "جوان"
        "07" -> "جويلية"
        "08" -> "أوت"
        "09" -> "سبتمبر"
        "10" -> "أكتوبر"
        "11" -> "نوفمبر"
        else -> "ديسمبر"
    }
    return "$arabicWeekDay $day $arabicMonth $year "

}
fun getArabicWeekDay(weekDay: String):String{
    return  when (weekDay) {
        "MONDAY" -> "الاثنين"
        "TUESDAY" -> "الثلاثاء"
        "WEDNESDAY" -> "الأربعاء"
        "THURSDAY" -> "الخميس"
        "FRIDAY" -> "الجمعة"
        "SATURDAY" -> "السبت"
        else -> "الأحد"
    }
}
 fun String.getDistance () : Int = this.split('-')[1].toInt()
 fun String.getSwimmingType() : String = this.split('-')[0]