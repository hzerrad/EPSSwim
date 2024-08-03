package com.example.epsswim.presentation.ui.trainer.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyPrimaryDark
import com.example.epsswim.presentation.ui.theme.MySecondary
import com.example.epsswim.presentation.utils.getArabicDate
import com.example.epsswim.presentation.utils.getArabicWeekDay
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun LevelCard(modifier: Modifier, title: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = { onClick.invoke() },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MySecondary
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ){
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Preview
@Composable
fun MyWeekCalendar(){
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val currentDate = remember { LocalDate.now() }
        val currentMonth = remember { YearMonth.now() }
        val startDate = remember { currentMonth.atStartOfMonth() } // Adjust as needed
        val endDate = remember { currentMonth.plusMonths(1).atEndOfMonth() } // Adjust as needed
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library


        val state = rememberWeekCalendarState(
            startDate = startDate,
            endDate = endDate,
            firstVisibleWeekDate = currentDate,
            firstDayOfWeek = firstDayOfWeek
        )
        val selectedDate = remember {
            mutableStateOf(currentDate)
        }
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp *0.95f
        val itemDp = screenWidth/7

       Surface (color = MyPrimaryDark) {
           Column (
               modifier = Modifier.padding(10.dp)
           ){
               Text(
                   modifier = Modifier.padding(bottom = 25.dp),
                   text = getArabicDate(selectedDate.value),
                   fontFamily = FontFamily(listOf(Font(R.font.cairo_bold))),
                   fontSize = 18.sp,
                   color = MyBackground
               )
               WeekCalendar(
                   state = state,
                   calendarScrollPaged = false,
                   dayContent = {
                       Day(
                           day = it,
                           selected = it.date == selectedDate.value,
                           dp = itemDp
                       ){
                           selectedDate.value = it.date
                       }
                   }
               )
           }
       }
    }


}
@Composable
fun Day(day: WeekDay, selected: Boolean = false, dp: Dp,onClick: () -> Unit) {
    Box(
        modifier =
        Modifier
            .clickable { onClick() }
            .width(dp)
            .padding(end = 5.dp)
            .background(if (!selected) MyPrimaryDark else MyBackground, RoundedCornerShape(12.dp)),
//            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Column (
            Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val colorText = if (!selected) MyBackground else MyPrimaryDark
            Text(
                text = getArabicWeekDay(day.date.dayOfWeek.name),
                color = colorText,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = day.date.dayOfMonth.toString(),
                color = colorText,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
//
        }
    }
}