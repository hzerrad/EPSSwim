package com.example.epsswim.presentation.ui.parent.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.parent.componants.MyTabRow
import com.example.epsswim.presentation.ui.parent.componants.SwimmerCard
import com.example.epsswim.presentation.ui.theme.MyBackground

@Preview
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            MyAppBar(
                title = stringResource(R.string.the_parent),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.logout_ic),
                            tint = MyBackground,
                            contentDescription = stringResource(R.string.logout)
                        )
                    }
                }
            ) }
    ) {
        Surface (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 25.dp)
            ) {
                var selectedIndex by remember {
                    mutableIntStateOf(1)
                }
                val tabsList = listOf(stringResource(R.string.professionals), stringResource(R.string.beginners))
                var itemCount by remember {
                    mutableIntStateOf(5)
                }

                MyTabRow(selectedIndex,tabsList){
                    selectedIndex = it
                    itemCount = 5 + it
                }
                LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                    items(itemCount){
                        SwimmerCard(Modifier.padding(start = 15.dp,end = 15.dp, bottom = 20.dp))
                    }
                }
            }
        }
    }
}

