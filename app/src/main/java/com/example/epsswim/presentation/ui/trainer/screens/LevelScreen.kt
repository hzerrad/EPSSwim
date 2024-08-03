package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.trainer.componants.MyWeekCalendar

@Preview
@Composable
fun LevelScreen (

){
    Scaffold (
        topBar = {
            MyAppBar(
                title = "المستوى 1",
                navigationIcon = {
                    IconButton( onClick = {}){
                        Icon(
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "back button"
                        )
                    }
                },
                actions = {
                    IconButton( onClick = {}){
                        Icon(
                            painter = painterResource(id = R.drawable.done_ic),
                            contentDescription = "done button"
                        )
                    }
                }
            )
        }

    ){
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            Column {
                MyWeekCalendar()
            }
        }
    }
}