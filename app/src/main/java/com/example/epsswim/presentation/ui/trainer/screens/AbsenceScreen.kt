package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.trainer.componants.LevelCard

@Composable
fun AbsenceScreen(navController: NavHostController) {
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
                    navController.navigate(Screen.LevelScreen)
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

