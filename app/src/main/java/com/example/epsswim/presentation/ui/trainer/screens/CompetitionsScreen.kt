package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.trainer.componants.CompetitionDetailsCard
import com.example.epsswim.presentation.ui.trainer.componants.FullScreenDialogContent
import com.example.epsswim.presentation.ui.trainer.componants.MySearchBar
import com.example.epsswim.presentation.ui.trainer.componants.ParticipantCard

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CompetitionsScreen(navController: NavHostController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val showFullScreenDialog = remember { mutableStateOf(false) }

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

