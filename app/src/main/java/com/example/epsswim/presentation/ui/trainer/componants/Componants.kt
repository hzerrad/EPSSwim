package com.example.epsswim.presentation.ui.trainer.componants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.presentation.ui.theme.MySecondary

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
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
