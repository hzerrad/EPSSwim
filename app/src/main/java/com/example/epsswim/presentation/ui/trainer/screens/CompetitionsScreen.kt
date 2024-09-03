package com.example.epsswim.presentation.ui.trainer.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.data.model.app.competition.Competition
import com.example.epsswim.data.model.app.swimmer.Level
import com.example.epsswim.data.model.app.swimmer.Swimmer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.trainer.componants.CompetitionDetailsCard
import com.example.epsswim.presentation.ui.trainer.componants.FullScreenDialogContent
import com.example.epsswim.presentation.ui.trainer.componants.MySearchBar
import com.example.epsswim.presentation.ui.trainer.componants.ParticipantCard
import com.example.epsswim.presentation.ui.trainer.viewmodels.CompetitionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionsScreen(
    navController: NavHostController,
    competitionViewModel: CompetitionViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val showFullScreenDialog = remember { mutableStateOf(false) }

    val competitionListState = competitionViewModel.competitionList.collectAsState()
    var competitionList by remember {
        mutableStateOf<List<Competition>>(emptyList())
    }
    var currentCompetition by remember {
        mutableStateOf<Competition?>(null)
    }
    LaunchedEffect(key1 = competitionListState.value) {
        if(competitionListState.value == null)
            competitionViewModel.getCompetitions()
        else
            competitionList = competitionListState.value?.data?.competitions ?: emptyList()
    }
    LaunchedEffect(key1 = true) {
        competitionViewModel.getTrainerSwimmers()
    }
    val levelListState = competitionViewModel.levelList.collectAsState()
    var levelList by remember {
        mutableStateOf<List<Level>>(emptyList())
    }
    var levelID by remember {
        mutableStateOf("")
    }
    val swimmerList = remember {
        mutableListOf<Swimmer>()
    }
    LaunchedEffect(key1 = levelListState.value) {
        if(levelListState.value != null){
            levelList = levelListState.value?.data?.levels ?: emptyList()
            levelList.forEach { lvl ->
                swimmerList.addAll(lvl.swimmers)
            }
            levelID = levelList.first().levelid
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold (
            topBar = { MyAppBar(title = stringResource(R.string.the_competitions)) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { showFullScreenDialog.value= true },
                    text = {
                        Text(
                            text = stringResource(R.string.add_competition),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular)))
                        ) },
                    icon = { Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_competition)) },
                    containerColor = MyPrimary,
                    contentColor = MyBackground
                )
            }
        ) {

            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                color = MyBackground
            ) {
                Column (Modifier.padding(horizontal = 20.dp, vertical = 30.dp)){
                    val searchedText = remember {
                        mutableStateOf("")
                    }
                    MySearchBar(
                        text = searchedText,
                        onSearchClicked = {
                            keyboardController?.hide()
                        },
                        onTextChange = { text ->
                            searchedText.value = text
                        }
                    )
                    LazyColumn (modifier = Modifier.padding(top = 40.dp)) {
                        items(items = competitionList.filter {it.event.contains(searchedText.value,ignoreCase = true)}){ competition ->
                            CompetitionCard(
                                modifier = Modifier.padding(bottom = 20.dp),
                                competition = competition
                            ) {
                                currentCompetition = competition
                                showBottomSheet= true
                            }
                        }
                    }
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                showBottomSheet = false
                            },
                            sheetState = sheetState,
                            containerColor = MyBackground
                        ) {
                            Column (Modifier.padding(start = 24.dp, end = 24.dp, bottom = 50.dp)){
                                CompetitionDetailsCard(Modifier.padding(bottom = 12.dp),competition = currentCompetition!!)
                                Text(
                                    text = stringResource(R.string.the_participants),
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    modifier = Modifier.padding(bottom = 12.dp),
                                    fontSize = 20.sp,
                                )
                                LazyColumn {
                                    items(items = currentCompetition!!.participants){
                                        ParticipantCard(
                                            modifier = Modifier.padding(bottom = 12.dp),
                                            participant = it
                                        ){
                                            navController.navigate(Screen.ParticipationDetails(swimmerID = it.swimmer.swimmerid, competitionID = currentCompetition!!.competitionid))
                                        }
                                    }


                                }

                            }
                        }
                    }
                    if (showFullScreenDialog.value)
                        Dialog(
                            onDismissRequest = { showFullScreenDialog.value = false },
                            properties = DialogProperties(usePlatformDefaultWidth = false)
                        ){
                            FullScreenDialogContent(
                                participants= swimmerList.toList(),
                                levelID = levelID,
                                onDismiss ={
                                    showFullScreenDialog.value = false
                                },
                                onDone = {
                                    competitionViewModel.insertCompetition(it)
                                    showFullScreenDialog.value = false
                                },
                            )
                        }
                }
            }
        }
    }
}

