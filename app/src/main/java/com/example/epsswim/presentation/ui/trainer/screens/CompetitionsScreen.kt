package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MySecondary
import com.example.epsswim.presentation.ui.trainer.componants.MySearchBar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CompetitionsScreen(){
    val keyboardController = LocalSoftwareKeyboardController.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val showFullScreenDialog = remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold (
            topBar = { MyAppBar(title = stringResource(R.string.the_competitions)) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {  },
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
                        items(3){
                            CompetitionCard(modifier = Modifier.padding(bottom = 20.dp), name = "المسابقة الولائية", date = "10/12/2024") {
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
                                CompetitionDetailsCard(Modifier.padding(bottom = 12.dp))
                                Text(
                                    text = stringResource(R.string.the_participants),
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black,
                                    modifier = Modifier.padding(bottom = 12.dp),
                                    fontSize = 20.sp,
                                )
                                ParticipantCard(Modifier.padding(bottom = 12.dp)){

                                }
                                ParticipantCard(Modifier.padding(bottom = 12.dp)){

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
                                onDismiss ={
                                    showFullScreenDialog.value = false
                                },
                                onDone = {
                                    showFullScreenDialog.value = false
                                },
                            )
                        }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FullScreenDialogContent( onDismiss: () -> Unit={}, onDone: () -> Unit={}) {
    Surface (color = MyBackground) {
        Column (
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
        ) {
            DialogHeader(
                onDismiss =onDismiss,
                onDone =onDone
            )
            DialogBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogBody() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var isCardSelected by remember {mutableStateOf(false)}

    Column (
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                isCardSelected = false
                focusManager.clearFocus(true)
            }
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = stringResource(R.string.personal_info),
            fontSize = 16.sp,
            color = MyPrimary,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
        )
        var name by rememberSaveable { mutableStateOf("") }
        var nameTyped by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameTyped = true
            },
            label = { Text(stringResource(R.string.competition_name)) },
            isError = name.isEmpty() && nameTyped,
            modifier = Modifier
                .onFocusChanged { isCardSelected = false }
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MyPrimary,
                focusedContainerColor = MyBackground ,
                unfocusedContainerColor = MyBackground ,
                focusedLabelColor = MyPrimary
            )
        )
        var place by rememberSaveable { mutableStateOf("") }
        var placeTyped by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            value = place,
            onValueChange = {
                place = it
                placeTyped = true
            },
            label = { Text(stringResource(R.string.competition_place)) },
            isError = name.isEmpty() && nameTyped,
            modifier = Modifier
                .onFocusChanged { isCardSelected = false }
                .padding(bottom = 12.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MyPrimary,
                focusedContainerColor = MyBackground ,
                unfocusedContainerColor = MyBackground ,
                focusedLabelColor = MyPrimary
            )
        )
        val initialDate = Calendar
            .getInstance()
            .apply {
                set(2024, Calendar.JANUARY, 1,1, 0, 0)
            }
            .timeInMillis
        val competitionDate =  rememberSaveable { mutableStateOf("02/01/2024") }

        CustomDatePicker(
            modifier = Modifier
                .onFocusChanged { isCardSelected = false }
                .padding(bottom = 12.dp),
            datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = initialDate,
                yearRange = 2024..2034
            ),
            label = stringResource(R.string.competition_date),
//            isIllegalInput = isDateNotValid(dateOfBorrowState.value,dateOfReturnState.value),
            isIllegalInput = false,
            dateState = competitionDate
        )
        var isBrevet by remember {mutableStateOf(false)}
        OutlinedCard(
            modifier = Modifier
                .selectable(
                    indication = null,
                    interactionSource = interactionSource,
                    selected = isCardSelected,
                    onClick = {
                        focusManager.clearFocus(true)
                        isCardSelected = !isCardSelected
                    }
                )
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(4.dp),
            border = if (isCardSelected) BorderStroke(2.dp, MyPrimary) else BorderStroke(1.dp, Color.DarkGray),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MyBackground
            ),
        ) {
            Row (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(R.string.brevet), fontSize = 16.sp)
                Switch(
                    checked = isBrevet,
                    onCheckedChange = {
                        isBrevet = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MyBackground,
                        checkedTrackColor = MyPrimary,
                        checkedBorderColor = MyPrimary,
                        uncheckedTrackColor = MyBackground
                    )
                )
            }
        }
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = stringResource(R.string.the_participants),
            fontSize = 16.sp,
            color = MyPrimary,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
        )
        val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        var expanded by remember { mutableStateOf(true) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            modifier = Modifier.padding(bottom = 12.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            }
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text(stringResource(id = R.string.the_participator)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MyPrimary,
                    focusedContainerColor = MyBackground ,
                    unfocusedContainerColor = MyBackground ,
                    focusedLabelColor = MyPrimary
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        text = {
                            Text(text = selectionOption)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DialogHeader(onDismiss: () -> Unit, onDone: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .padding(bottom = 16.dp)
    ) {
        IconButton(onClick = { onDismiss.invoke() }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(R.string.close))
        }
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = stringResource(id = R.string.add_competition),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
        )
        Box(modifier = Modifier.fillMaxWidth()){
            TextButton(
                modifier =  Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onDone()
                },
            ) {
                Text(text = stringResource(R.string.save), fontSize = 16.sp, color = MyPrimary)
            }
        }
    }
}

@Composable
fun ParticipantCard(modifier: Modifier,onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, MySecondary)
    ){
        Row (
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(65.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(0.2.dp, Color.Black),
                painter = painterResource(id = R.drawable.img),
                contentDescription = stringResource(R.string.profile_img),
                contentScale = ContentScale.Crop
            )
            Column  {
                Text(
                    text = "محمد عليم",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "مستوى جونيور (18 سنة)",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CompetitionDetailsCard(modifier: Modifier) {
    Row (
        modifier= modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "المسابقة الولائية",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
            )
            Text(
                text = "12/12/2023",
                color = Color.Gray,
                fontSize = 16.sp,
            )
            Text(
                text = "سطيف, عين أرنات",
                color = Color.Gray,
                fontSize = 16.sp,
            )
        }
        Image(
            modifier = Modifier.height(50.dp),
            painter = painterResource(id = R.drawable.competition_badge),
            contentDescription = "competition badge",
            contentScale = ContentScale.Crop
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun CustomDatePickerDialog (
    state: DatePickerState,
    confirmButtonText: String = stringResource(R.string.ok),
    dismissButtonText: String = stringResource(R.string.cancel),
    onDismissRequest: () -> Unit,
    onConfirmButtonClicked: (Long?) -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = { //OK
            TextButton(onClick = { onConfirmButtonClicked(state.selectedDateMillis) }) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = { //cancel
            TextButton(onClick = onDismissRequest) {
                Text(text = dismissButtonText)
            }
        },
        content = {
            DatePicker(
                state = state,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        },
        colors = DatePickerDefaults.colors(
            containerColor = MyBackground,

        )
    )
}
@Composable
private fun DateTextField (
    modifier: Modifier = Modifier,
    text: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    isEnabled: Boolean = true,
    label: String,
//    imeAction: ImeAction = ImeAction.Next,
//    keyboardType: KeyboardType = KeyboardType.Number,
//    keyBoardActions: KeyboardActions = KeyboardActions(),
//    supportingText: String ,
    isIllegalInput: Boolean, //error state
    readOnly: Boolean,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 18.sp),
//        keyboardActions = keyBoardActions,
//        keyboardOptions = KeyboardOptions(
//            imeAction = imeAction,
//            keyboardType = keyboardType
//        ),
        enabled = isEnabled,
        trailingIcon = trailingIcon,
        label = {
            Text(text = label, style = TextStyle(fontSize = 18.sp))
        },
        singleLine = true,
        isError = isIllegalInput,
        readOnly = readOnly,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MyPrimary,
            focusedContainerColor = MyBackground ,
            unfocusedContainerColor = MyBackground ,
            focusedLabelColor = MyPrimary
        )
    )
}
@ExperimentalMaterial3Api
@Composable
fun CustomDatePicker (
    modifier: Modifier,
    datePickerState: DatePickerState,
    label: String,
    isIllegalInput: Boolean,
    dateState: MutableState<String>
) {
    val pattern = "dd/MM/yyyy"
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    val formattedDate = formatter.format(Date(datePickerState.selectedDateMillis!!))

    dateState.value = formattedDate
    val isOpen = rememberSaveable { mutableStateOf(false) }

    DateTextField(
        text = dateState.value,
        modifier = modifier,
        label = label,
        onChange = {
            dateState.value = it
        },
        trailingIcon = {
            IconButton(onClick = { isOpen.value = true}) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date Picker")
                if (isOpen.value) {
                    CustomDatePickerDialog(
                        state = datePickerState,
                        onConfirmButtonClicked = {
                            isOpen.value = false
                            if (it != null) {
                                dateState.value = formatter.format(Date(it))
                            }
                        },
                        onDismissRequest = { isOpen.value = false },
                    )
                }
            }
        },
        isIllegalInput = isIllegalInput,
        readOnly = true
    )
}