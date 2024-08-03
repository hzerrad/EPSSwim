package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MySecondary

@Preview
@Composable
fun AbsenceScreen (

) {
    Scaffold (
        topBar = { MyAppBar(title = stringResource(R.string.the_parent)) }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.End
            ){
                Text(
                    modifier = Modifier.padding(bottom = 30.dp),
                    text = stringResource(R.string.levels),
                    fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                    fontSize = 24.sp,
                )
                LevelCard(
                    modifier=Modifier.padding(bottom = 30.dp),
                    title = "المستوى 1 : مبتدئ"
                ){

                }
                LevelCard(
                    modifier=Modifier.padding(bottom = 30.dp),
                    title = "المستوى 1 : مبتدئ"
                ){

                }
                LevelCard(
                    modifier=Modifier.padding(bottom = 30.dp),
                    title = "المستوى 1 : مبتدئ"
                ){

                }
            }
        }
    }
}

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
