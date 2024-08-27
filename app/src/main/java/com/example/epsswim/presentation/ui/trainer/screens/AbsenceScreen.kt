package com.example.epsswim.presentation.ui.trainer.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.data.model.app.swimmer.Level
import com.example.epsswim.data.model.app.swimmer.Swimmer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.trainer.componants.LevelCard
import com.example.epsswim.presentation.ui.trainer.viewmodels.TrainerViewModel

@Composable
fun AbsenceScreen(
    navController: NavHostController,
    trainerViewModel: TrainerViewModel = hiltViewModel()
) {
    val levelsListState = trainerViewModel.levelList.collectAsState()
    var levelsList by remember {
        mutableStateOf<List<Level>>(emptyList())
    }
    LaunchedEffect(key1 = levelsListState.value == null) {
        if (levelsListState.value != null){
            levelsList = levelsListState.value?.data?.levels ?: emptyList()
            Log.d("TAG", "AbsenceScreen: $levelsList")
        }
    }
    Scaffold (
        topBar = { MyAppBar(title = stringResource(R.string.absences)) }
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
                LazyColumn {
                    items(items = levelsList){
                        LevelCard(
                            modifier=Modifier.padding(bottom = 30.dp),
                            title = it.levelname
                        ){
                            navController.navigate(Screen.LevelScreen)
                        }
                    }
                }
            }
        }
    }
}

