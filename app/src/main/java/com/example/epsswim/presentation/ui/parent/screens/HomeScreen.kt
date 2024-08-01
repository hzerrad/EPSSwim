package com.example.epsswim.presentation.ui.parent.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.parent.componants.MyTabRow

@Preview
@Composable
fun HomeScreen () {
    Scaffold (
        topBar = { MyAppBar(title = stringResource(R.string.the_parent)) }
    ) {
        Surface (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
                var selectedIndex by remember {
                    mutableIntStateOf(1)
                }
                val tabsList = listOf(stringResource(R.string.professionals), stringResource(R.string.beginners))

                MyTabRow(selectedIndex,tabsList){
                    selectedIndex = it

                }
            }
        }
    }
}

