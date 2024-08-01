package com.example.epsswim.presentation.ui.parent.componants

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary

@Composable
fun MyTabRow(selectedIndex: Int, tabsList : List<String>,onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        tabsList.forEachIndexed { index, s ->
            TabItem(
                text = s,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .width(100.dp),
                isSelected = selectedIndex == index
            ) {
                onClick(index)
            }
        }
    }
}

@Composable
fun TabItem(text: String, isSelected :Boolean, modifier: Modifier, onClick: () -> Unit) {

    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            MyBackground
        } else {
            Color.Gray
        },
        animationSpec = tween(easing = LinearEasing),
        label = "Tab text color"
    )
    val tabBgColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            MyPrimary
        } else {
            Color.Gray.copy(0.1f)
        },
        animationSpec = tween(easing = LinearOutSlowInEasing),
        label = "Tab bg color"
    )
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(tabBgColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
            color = tabTextColor,
            fontSize = 16.sp,
        )
    }
}
