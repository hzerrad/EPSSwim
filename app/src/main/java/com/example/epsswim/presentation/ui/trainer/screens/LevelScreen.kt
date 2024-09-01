package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.data.model.app.swimmer.Swimmer
import com.example.epsswim.data.model.requestBody.absences.SwimmerId
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.Loading
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyPrimaryDark
import com.example.epsswim.presentation.ui.theme.MySecondary
import com.example.epsswim.presentation.ui.trainer.componants.AbsenceSwimmerCard
import com.example.epsswim.presentation.ui.trainer.componants.MyWeekCalendar
import com.example.epsswim.presentation.ui.trainer.viewmodels.TrainerViewModel
import com.example.epsswim.presentation.utils.getDate
import com.example.epsswim.presentation.utils.rememberImeState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelScreen(
    navController: NavHostController,
    levelName:String,
    levelID: String,
    trainerViewmodel: TrainerViewModel
) {
    val selectedDate = remember {
        mutableStateOf(LocalDate.now())
    }
    val isDataSent = remember {
        mutableStateOf(false)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val absentList = remember {
        mutableListOf<SwimmerId>()
    }
    val presentList = remember {
        mutableListOf<String>()
    }
    trainerViewmodel.getSwimmersByLevelId(levelID,getDate(selectedDate.value))

    LaunchedEffect(key1 = levelID,key2 = getDate(selectedDate.value)) {
        absentList.clear()
        presentList.clear()
        trainerViewmodel.getSwimmersByLevelId(levelID,getDate(selectedDate.value))
    }
    val swimmerListState = trainerViewmodel.swimmerList.collectAsState()
    var swimmerList by remember {
        mutableStateOf<List<Swimmer>>(emptyList())
    }

    var currentSwimmer by remember {
        mutableStateOf<Swimmer?>(null)
    }
    var index by remember {
        mutableIntStateOf(0)
    }
    var lastIndex by remember {
        mutableIntStateOf(0)
    }

    var trainerId by remember {
        mutableStateOf("")
    }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var note by remember {
        mutableStateOf("")
    }
    val noteState = rememberRichTextState()

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = swimmerListState.value) {
        if (swimmerListState.value != null){
            swimmerList = swimmerListState.value?.data?.swimmers ?: emptyList()
            absentList.addAll(
                swimmerList.filter {
                    it.swimmerAbsences_aggregate.aggregate.count != 0
                }.map {
                    SwimmerId(it.swimmerid)
                }
            )
            presentList.addAll(
                swimmerList.filter {
                    it.swimmerAbsences_aggregate.aggregate.count == 0
                }.map {
                    it.swimmerid
                }
            )
            note = swimmerListState.value?.data?.levels_by_pk?.notes?.firstOrNull()?.description ?: ""
            trainerId = swimmerListState.value?.data?.levels_by_pk?.trainerid ?: ""
            noteState.setMarkdown(note)
            lastIndex = swimmerList.size - 1
            currentSwimmer = swimmerList[index]
            isLoading.value = currentSwimmer != null
        }
    }
    LaunchedEffect (isDataSent.value,isError.value,isLoading.value) {
        if (!isLoading.value && isDataSent.value && !isError.value){
            absentList.clear()
            absentList.clear()
            navController.popBackStack()
        }
    }

    Scaffold (
        topBar = {
            MyAppBar(
                title = levelName,
                navigationIcon = {
                    IconButton( onClick = {
                        navController.popBackStack()
                    }){
                        Icon(
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "back button"
                        )
                    }
                },
                actions = {
                    IconButton( onClick = {
                        isDataSent.value = true
                        isLoading.value = true
                        trainerViewmodel.insertAbsencesAndNotes(
                            isLoading = isLoading,
                            isError=isError,
                            objects1 = absentList.toList(),
                            objects2 = presentList.toList(),
                            absencedate = getDate(selectedDate.value),
                            levelid = levelID,
                            trainerid = trainerId,
                            description = noteState.toMarkdown()

                        )
//                        Log.d("TAG", "LevelScreen: abs: ${absentList.toList()} pres: ${presentList.toList()}")

                    }){
                        Icon(
                            painter = painterResource(id = R.drawable.done_ic),
                            contentDescription = "done button"
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0,0,0,)


    ){



        LaunchedEffect(key1 = imeState.value) {
            if (imeState.value){
                scrollState.animateScrollTo(scrollState.maxValue, tween(300))
            }
        }

        if (isLoading.value)
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ){
                Column {
                    MyWeekCalendar(
                        selectedDate = selectedDate
                    )
                    Column (
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                            text = stringResource(R.string.number_of_presence) + presentList.size +"/"+ swimmerList.size.toString(),
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                            fontSize = 20.sp,
                        )
                        Box (modifier = Modifier.fillMaxWidth()){
                            if (index!=0)
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .clickable {
                                            index--
                                            currentSwimmer = swimmerList[index]
                                        }
                                        .width(28.dp)
                                        .height(180.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                            .padding(start = 10.dp),
                                        painter = painterResource(id = R.drawable.chevron_left) ,
                                        contentDescription ="back",
                                        tint = MyPrimaryDark
                                    )
                                }
                            if (index!= lastIndex)
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .clickable {
                                            index++
                                            currentSwimmer = swimmerList[index]
                                        }
                                        .width(28.dp)
                                        .height(180.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .padding(end = 10.dp),
                                        painter = painterResource(id = R.drawable.chevron_right) ,
                                        contentDescription ="next",
                                        tint = MyPrimaryDark
                                    )
                                }
                            AbsenceSwimmerCard(
                                modifier = Modifier
                                    .padding(horizontal = 36.dp)
                                    .align(Alignment.Center),
                                absentList = absentList,
                                presentList = presentList,
                                swimmer = currentSwimmer!!,
                                enabled = getDate(selectedDate.value) == getDate(LocalDate.now()),
                            ){
                                navController.navigate(Screen.SwimmerProfile(currentSwimmer!!.swimmerid,false))
                            }
                        }
                        Button(
                            onClick = {
                                showBottomSheet = true
                            },
                            modifier = Modifier
                                .padding(vertical = 30.dp, horizontal = 36.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MyPrimaryDark, contentColor = MyBackground),
                            elevation = ButtonDefaults.buttonElevation(10.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.add_note),
                                fontWeight = FontWeight.Medium,
                                fontSize = 20.sp,
                                color = MyBackground
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
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                                Column (modifier = Modifier.verticalScroll(scrollState)) {
                                    TextButton(
                                        onClick = {
                                            scope.launch {
                                                note = noteState.toMarkdown()
                                                sheetState.hide()
                                            }.invokeOnCompletion {
                                                showBottomSheet= false
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(top = 16.dp, end = 16.dp)
                                            .align(Alignment.End)
                                    ) {
                                        Text(
                                            text ="حفظ",
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp,
                                            color = MyPrimary
                                        )
                                    }
                                    if (getDate(selectedDate.value) == getDate(LocalDate.now()))
                                        MarkDownController(
                                            onBoldClick = {
                                                noteState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
                                            },
                                            onItalicClick = {
                                                noteState.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
                                            },
                                            onUnderlineClick = {
                                                noteState.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                                            },
                                            onTitleClick = {
                                                noteState.toggleSpanStyle(SpanStyle(fontSize = 24.sp))
                                            },

                                            onTextColorClick = {
                                                noteState.toggleSpanStyle(SpanStyle(color = Color.Red))
                                            }
                                        )
                                    RichTextEditor(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth(),
                                        state = noteState,
                                        enabled = getDate(selectedDate.value) == getDate(LocalDate.now()),
                                        placeholder = {
                                            Text(text = "أضف ملاحظة...")
                                        },
                                        minLines = 10,
                                        colors = RichTextEditorDefaults.richTextEditorColors(
                                            containerColor = MyBackground,
                                            cursorColor = MyPrimaryDark,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        )
                                    )


                                }
                            }

                        }
                    }
                }
            }
        else
            Loading()
    }
}

@Composable
fun MarkDownController(
    modifier: Modifier = Modifier,
    onBoldClick: () -> Unit,
    onItalicClick: () -> Unit,
    onUnderlineClick: () -> Unit,
    onTitleClick: () -> Unit,
    onTextColorClick: () -> Unit,
) {
    var boldSelected by rememberSaveable { mutableStateOf(false) }
    var italicSelected by rememberSaveable { mutableStateOf(false) }
    var underlineSelected by rememberSaveable { mutableStateOf(false) }
    var titleSelected by rememberSaveable { mutableStateOf(false) }
    var textColorSelected by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ControlWrapper(
            selected = boldSelected,
            onChangeClick = { boldSelected = it },
            onClick = onBoldClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.format_bold),
                contentDescription = "Bold Control",
                tint = MyPrimary
            )
        }
        ControlWrapper(
            selected = italicSelected,
            onChangeClick = { italicSelected = it },
            onClick = onItalicClick
        ) {
            Icon(
                painter = painterResource(id =R.drawable.format_italic ),
                contentDescription = "Italic Control",
                tint = MyPrimary
            )
        }
        ControlWrapper(
            selected = underlineSelected,
            onChangeClick = { underlineSelected = it },
            onClick = onUnderlineClick
        ) {
            Icon(
                painter = painterResource(id =R.drawable.format_underlined ),
                contentDescription = "Underline Control",
                tint = MyPrimary
            )
        }
        ControlWrapper(
            selected = titleSelected,
            onChangeClick = { titleSelected = it },
            onClick = onTitleClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.format_title ),
                contentDescription = "Title Control",
                tint = MyPrimary
            )
        }
        ControlWrapper(
            selected = textColorSelected,
            onChangeClick = { textColorSelected = it },
            onClick = onTextColorClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.format_text_xolor ),
                contentDescription = "TextColor Control",
                tint = MyPrimary
            )
        }
    }
}

@Composable
fun ControlWrapper(
    selected: Boolean,
    selectedColor: Color = MySecondary,
    unselectedColor: Color = MyBackground,
    onChangeClick: (Boolean) -> Unit,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(size = 6.dp))
            .clickable {
                onClick()
                onChangeClick(!selected)
            }
            .background(
                if (selected) selectedColor
                else unselectedColor
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 6.dp)
            )
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
