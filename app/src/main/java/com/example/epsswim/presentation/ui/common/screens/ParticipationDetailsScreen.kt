package com.example.epsswim.presentation.ui.common.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.collection.LongList
import androidx.collection.mutableLongListOf
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.epsswim.R
import com.example.epsswim.data.model.app.participation.ParticipationResponse
import com.example.epsswim.data.model.app.participation.swimmingtypes.Eventtype
import com.example.epsswim.data.model.requestBody.participation.ParticipationVariables
import com.example.epsswim.presentation.model.StopWatch
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionParticipationCard
import com.example.epsswim.presentation.ui.common.componants.Loading
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyRed
import com.example.epsswim.presentation.ui.trainer.componants.ExposedDropdownMenuParticipationType
import com.example.epsswim.presentation.ui.common.viewmodels.ParticipationViewModel
import com.example.epsswim.presentation.ui.trainer.componants.ActionsMenu
import com.example.epsswim.presentation.utils.calculateAge
import com.example.epsswim.presentation.utils.formatTime
import com.example.epsswim.presentation.utils.getFullName
import com.example.epsswim.presentation.utils.parseTimeToMillis
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ParticipationDetailsScreen(
    navController: NavHostController,
    participationViewModel: ParticipationViewModel,
    isTrainer: MutableState<Boolean?>,
    swimmerID: String,
    competitionID : String
) {
    val swimmingTypesState = participationViewModel.swimmingTypes.collectAsStateWithLifecycle()
    var swimmingTypes by remember {
        mutableStateOf<List<Eventtype>?>(null)
    }
    LaunchedEffect(key1 = swimmingTypesState.value) {
        if(swimmingTypesState.value == null)
            participationViewModel.getSwimmingTypes()
        else
            swimmingTypes = swimmingTypesState.value?.data?.eventtypes ?: emptyList()
    }
    val participationState = participationViewModel.participation.collectAsStateWithLifecycle()

    var participation by remember {
        mutableStateOf<ParticipationResponse?>(null)
    }
    LaunchedEffect(key1 = participationState.value) {
        if(participationState.value == null)
            participationViewModel.getParticipation(swimmerID,competitionID)
        else{
            participation = participationState.value
        }
    }


    Scaffold (
        topBar = {
            MyAppBar(
                title = stringResource(R.string.participation_details),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            participationViewModel.clearState()
                            navController.navigateUp()
                        }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "back button"
                        )
                    }
                }
            ) }
    ) {
        BackHandler {
            participationViewModel.clearState()
            navController.navigateUp()
        }
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        if (participation == null) {
            Loading()
        }
        else {
            Surface (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ){
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
                    Column(
                        Modifier
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.the_participator),
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                            fontSize = 20.sp,
                            color = MyPrimary
                        )
                        CompetitionParticipationCard(Modifier.padding(bottom = 15.dp)){
                            Row (
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            Screen.SwimmerProfile(
                                                participation!!.data.swimmers_by_pk.swimmerid,
                                                isParent = false
                                            )
                                        )
                                    }
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                AsyncImage(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .size(65.dp)
                                        .border(0.2.dp, Color.Black, RoundedCornerShape(12.dp)),
                                    model =  participation!!.data.swimmers_by_pk.pfpUrl,
                                    error = painterResource(id = R.drawable.img),
                                    fallback =  painterResource(id = R.drawable.img),
                                    contentDescription = stringResource(R.string.profile_img),
                                    contentScale = ContentScale.Crop
                                )
                                Column  {
                                    Text(
                                        text = getFullName(participation!!.data.swimmers_by_pk.firstname, participation!!.data.swimmers_by_pk.lastname),
                                        fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                                        fontSize = 18.sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "مستوى "+ participation!!.data.swimmers_by_pk.level.levelname +" ( ${calculateAge(participation!!.data.swimmers_by_pk.birthday)} سنة)",
                                        fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                        Text(
                            text = stringResource(R.string.the_competition),
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                            fontSize = 20.sp,
                            color = MyPrimary
                        )
                        CompetitionParticipationCard(Modifier.padding(bottom = 15.dp)){
                            Row (
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = participation!!.data.competitions_by_pk.event,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )

                                    Text(
                                        text = participation!!.data.competitions_by_pk.competitiondate.replace("-","/"),
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = participation!!.data.competitions_by_pk.location,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    if (participation!!.data.competitions_by_pk.isbrevet)
                                        Text(
                                            text = "مسابقة شهادة",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = Color.Gray
                                        )
                                }
                                Image(
                                    modifier = Modifier.height(56.dp),
                                    painter = painterResource(id = if (participation!!.data.competitions_by_pk.isbrevet) R.drawable.competition_badge1 else R.drawable.competition_badge),
                                    contentDescription = "competition badge",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Text(
                            text = stringResource(R.string.the_participation),
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                            fontSize = 20.sp,
                            color = MyPrimary
                        )
                         participation!!.data.swimmerevents.forEach{
                            CompetitionParticipationCard(Modifier.padding(bottom = 15.dp)){
                                val expanded = remember { mutableStateOf(false) }
                                Column (
                                    modifier = Modifier
                                        .combinedClickable(
                                            onLongClick = {
                                                if (isTrainer.value!!)
                                                    expanded.value = !expanded.value
                                            },
                                            onClick = {}
                                        )
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                ) {
                                    ActionsMenu(
                                        modifier = Modifier.align(Alignment.End),
                                        isEditable = false,
                                        expanded = expanded ,
                                        onDeleteClick = {  },
                                    )
                                    Row (
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    ) {
                                        Text(
                                            text = it.eventtype.eventname,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )
                                        Icon(
                                            painter = painterResource(id = R.drawable.free_style_nage_ic),
                                            contentDescription = "swimming type",
                                            tint = Color(0xff138dff),
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                    it.laptimes.forEachIndexed { index,laptime ->
                                        Text(
                                            text = "التوقف "+ (index+1) +": ${formatTime(laptime)}",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = Color.Black
                                        )
                                    }

                                }
                            }

                        }
                        if (isTrainer.value!!){
                            Button(
                                onClick = {
                                    showBottomSheet = true
                                },
                                modifier = Modifier
                                    .padding(bottom = 24.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MyPrimary, contentColor = MyBackground),
                                elevation = ButtonDefaults.buttonElevation(3.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.add_participation),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 20.sp,
                                )
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
                                ParticipationSheetContent(swimmingTypes!!){ swimmingTypeID,laptimes ->
                                    participationViewModel.insertParticipation(
                                        ParticipationVariables(
                                            competitionid = competitionID,
                                            swimmerid = swimmerID,
                                            eventtypeid = swimmingTypeID,
                                            laptimes = laptimes
                                        )
                                    )
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        showBottomSheet= false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ParticipationSheetContent(swimmingTypes:List<Eventtype>,onClick : (String,List<Long>) -> Unit) {
    val stops = rememberSaveable {
        mutableListOf<String>()
    }
    var swimType by remember {
        mutableStateOf(swimmingTypes.first())
    }
    val lapTimes = remember {
       mutableListOf<Long>()
    }

    val stopWatch = remember {
        StopWatch()
    }
    var stopsNum by remember {
        mutableIntStateOf(0)
    }
    var stopWatchTime by remember {
        mutableStateOf("00.00.000")
    }
    var isStopWatchActive by remember {
        mutableStateOf(false)
    }
    var isTimeSetToZero by remember {
        mutableStateOf(true)
    }
    stopWatchTime = stopWatch.formattedTime
    isStopWatchActive = stopWatch.isActive
    Column (
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextButton(
            onClick = {
                onClick(swimType.eventtypeid,lapTimes.toList())
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = stringResource(id = R.string.save),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MyPrimary
            )
        }
        Text(
            text = stringResource(id = R.string.participation_type),
            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
            modifier = Modifier.padding(bottom = 12.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        ExposedDropdownMenuParticipationType(
            swimmingTypes=swimmingTypes,
            modifier = Modifier.padding(bottom = 12.dp),
        ){
            swimType = it
        }
        Text(
            text = stringResource(id = R.string.the_results),
            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
            modifier = Modifier.padding(bottom = 12.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        val maxIndex = (swimType.distance/50) - 1
        FlowRow(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            maxItemsInEachRow = 2
        ){

            for (index in 0..maxIndex ){
                val stopDistance = (index+1) * 50
                stops.add("")
                OutlinedTextField(
                    value = stops[index],
                    onValueChange = {
                        stops[index] = it
                    },
                    readOnly = true,
                    label = { Text("وقت المرور $stopDistance") },
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth(0.485f),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MyPrimary,
                        focusedContainerColor = MyBackground ,
                        unfocusedContainerColor = MyBackground ,
                        focusedLabelColor = MyPrimary
                    )
                )
            }

        }
        Text(
            text = stringResource(id = R.string.stop_watch),
            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
            modifier = Modifier.padding(bottom = 12.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        Text(
            text = stopWatchTime,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
            fontSize = 68.sp,
            color = MyRed
        )
        Row (
            modifier = Modifier
                .padding(bottom = 150.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AnimatedVisibility(visible = isStopWatchActive) {
                Button(
                    onClick = {
                        if (stopsNum < maxIndex){
                            stops[stopsNum] = stopWatchTime
                            if (stopsNum in lapTimes.indices)
                                lapTimes[stopsNum] = parseTimeToMillis(stopWatchTime)
                            else
                                lapTimes.add(parseTimeToMillis(stopWatchTime))
                            stopsNum++
                        } else {
                            stops[stopsNum] = stopWatchTime
                            if (stopsNum in lapTimes.indices)
                                lapTimes[stopsNum] = parseTimeToMillis(stopWatchTime)
                            else
                                lapTimes.add(parseTimeToMillis(stopWatchTime))
                            stopWatch.pause()
                            stopsNum = 0
                        }
                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MyPrimary, contentColor = MyBackground),
                    elevation = ButtonDefaults.buttonElevation(3.dp),
                ) {
                    Text(
                        text = stringResource(R.string.lap),
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                    )
                }
            }

            Button(
                onClick = {
                    if (isStopWatchActive){
                        stopWatch.pause()
                    }else{
                        stopWatch.start()
                        isTimeSetToZero = false
                    }
                },
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MyPrimary, contentColor = MyBackground),
                elevation = ButtonDefaults.buttonElevation(3.dp),
            ) {
                Text(
                    text = if (!isStopWatchActive) stringResource(R.string.start) else stringResource(R.string.stop),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
            }
            AnimatedVisibility(visible = !isTimeSetToZero) {
                Button(
                    onClick = {
                        stopWatch.reset()
                        lapTimes.clear()
                        stopsNum = 0
                        isTimeSetToZero = true
                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MyPrimary, contentColor = MyBackground),
                    elevation = ButtonDefaults.buttonElevation(3.dp),
                ) {
                    Text(
                        text = stringResource(R.string.reset),
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}


