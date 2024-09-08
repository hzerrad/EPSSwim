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
import com.example.epsswim.data.model.requestBody.competition.CompetitionData
import com.example.epsswim.data.model.requestBody.competition.CompetitionParticipant
import com.example.epsswim.data.model.requestBody.competition.Data
import com.example.epsswim.data.model.requestBody.competition.Participants
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.Loading
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.common.componants.NoDataScreen
import com.example.epsswim.presentation.ui.common.componants.NotConnectedScreen
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.trainer.componants.CompetitionDetailsCard
import com.example.epsswim.presentation.ui.trainer.componants.FullScreenDialogContent
import com.example.epsswim.presentation.ui.trainer.componants.MySearchBar
import com.example.epsswim.presentation.ui.trainer.componants.ParticipantCard
import com.example.epsswim.presentation.ui.trainer.viewmodels.CompetitionViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionsScreen(
    navController: NavHostController,
    competitionViewModel: CompetitionViewModel
) {
    val isNotConnected by competitionViewModel.isNotConnected
    var isLoading by remember {
        mutableStateOf(true)
    }
    var isDataPresent by remember {
        mutableStateOf(false)
    }
    var isEditMode by remember {
        mutableStateOf(false)
    }
    var competitionIdEditMode by remember {
        mutableStateOf<String?>(null)
    }
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
        else{
            isLoading = false
            competitionList = competitionListState.value?.data?.competitions ?: emptyList()
            if (competitionList.isNotEmpty())
                isDataPresent = true
        }

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
    val competitionData = remember {
        mutableStateOf(
            CompetitionData(
                competitiondate = "2024-01-01",
                event = "",
                location = "",
                participants = Participants(emptyList()),
                isbrevet = false,
                levelid = levelID
            )
        )
    }
    LaunchedEffect(key1 = levelListState.value) {
        if(levelListState.value != null){
            isLoading = false
            levelList = levelListState.value?.data?.levels ?: emptyList()
            if (levelList.isNotEmpty()){
                levelList.forEach { lvl ->
                    swimmerList.addAll(lvl.swimmers)
                }
                levelID = levelList.first().levelid
            }
        }
    }
    LaunchedEffect(isNotConnected) {
        if (isNotConnected){
            delay(1000)
            isLoading = false
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold (
            topBar = { MyAppBar(title = stringResource(R.string.the_competitions)) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        competitionData.value = CompetitionData(
                            competitiondate = "2024-01-01",
                            event = "",
                            location = "",
                            participants = Participants(emptyList()),
                            isbrevet = false,
                            levelid = levelID
                        )
                        showFullScreenDialog.value= true
                              },
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
            if (isLoading)
                Loading()
            else if (isNotConnected)
                NotConnectedScreen()
            else if (!isDataPresent)
                NoDataScreen()
            else{
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
                                    competition = competition,
                                    isTrainer = true,
                                    onEdit = {
                                        isEditMode = true
                                        competitionIdEditMode = competition.competitionid
                                        competitionData.value =CompetitionData(
                                            competitiondate = competition.competitiondate,
                                            event = competition.event,
                                            isbrevet = competition.isbrevet,
                                            location = competition.location,
                                            participants =  Participants(competition.participants.map {
                                                Data(it.swimmer.swimmerid)
                                            }),
                                            levelid = levelID
                                        )
                                        showFullScreenDialog.value =true

                                    },
                                    onDelete = {
                                        competitionViewModel.deleteCompetition(competition.competitionid)
                                    },
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
                                    competitionData = competitionData ,
                                    participants= swimmerList.toList(),
                                    onDismiss ={
                                        showFullScreenDialog.value = false
                                    },
                                    onDone = {
                                        if (isEditMode){
                                            val updatedSwimmers = it.participants!!.data.map { data->
                                                CompetitionParticipant(competitionIdEditMode!!,data.swimmerid)
                                            }
                                            competitionViewModel.updateCompetition(it.copy(competitionid = competitionIdEditMode, objects = updatedSwimmers, participants = null))
                                            isEditMode = false
                                            competitionIdEditMode = null
                                            showFullScreenDialog.value = false
                                        }
                                        else{
                                            competitionViewModel.insertCompetition(it)
                                            showFullScreenDialog.value = false
                                        }
                                    },
                                )
                            }
                    }
                }
            }

        }
    }
}

