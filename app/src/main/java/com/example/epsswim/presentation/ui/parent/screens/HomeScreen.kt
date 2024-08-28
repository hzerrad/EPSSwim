package com.example.epsswim.presentation.ui.parent.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.data.model.app.swimmer.Swimmer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.common.viewmodels.AuthViewmodel
import com.example.epsswim.presentation.ui.common.viewmodels.SharedViewModel
import com.example.epsswim.presentation.ui.parent.componants.MyTabRow
import com.example.epsswim.presentation.ui.parent.componants.SwimmerCard
import com.example.epsswim.presentation.ui.parent.viewmodels.ParentViewModel
import com.example.epsswim.presentation.ui.theme.MyBackground

@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewmodel,
    parentViewModel: ParentViewModel ,
) {
    val swimmerListState = parentViewModel.swimmerList.collectAsState()
    var swimmerList by remember {
        mutableStateOf<List<Swimmer>>(emptyList())
    }
    LaunchedEffect(key1 = swimmerListState.value == null) {
        if (swimmerListState.value != null){
            swimmerList = swimmerListState.value?.data?.swimmers ?: emptyList()
            Log.d("TAG", "HomeScreen: $swimmerList")
        }
    }

    Scaffold (
        topBar = {
            MyAppBar(
                title = stringResource(R.string.the_parent),
                actions = {
                    IconButton(onClick = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login)
                    }) {
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


                MyTabRow(selectedIndex,tabsList){ index->
                    selectedIndex = index
                }
                if (swimmerListState.value != null)
                    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                        items(
                            items = swimmerList.filter {
                                if (selectedIndex==1){
                                    !it.ispro
                                }
                                else{
                                    it.ispro
                                }
                            }
                        ){
                            SwimmerCard(
                                swimmer = it,
                                modifier = Modifier.padding(start = 15.dp,end = 15.dp, bottom = 20.dp)
                            ){
                                navController.navigate(Screen.SwimmerProfile(it.swimmerid))
                            }
                        }
                    }
            }
        }
    }
}

