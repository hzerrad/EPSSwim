package com.example.epsswim.presentation.ui.common.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionParticipationCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.trainer.componants.ExposedDropdownMenuParticipationType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipationDetailsScreen(
    navController: NavHostController,
    isTrainer: Boolean
) {
    Scaffold (
        topBar = {
            MyAppBar(
                title = stringResource(R.string.participation_details),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
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
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        Surface (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
                Column(
                    Modifier
                        .padding(horizontal = 20.dp, vertical = 30.dp)
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
                                    navController.navigate(Screen.SwimmerProfile)
                                }
                                .padding(16.dp),
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
                                    fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "مستوى جونيور (18 سنة)",
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
                                        text = "المسابقة الولائية",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )

                                    Text(
                                        text = "08/10/2023",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "سطيف, عين ارنات",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = "مسابقة شهادة",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                            Image(
                                modifier = Modifier.height(56.dp),
                                painter = painterResource(id = R.drawable.competition_badge),
                                contentDescription = "competition badge",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Text(
                        text = stringResource(R.string.the_participator),
                        fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
                        fontSize = 20.sp,
                        color = MyPrimary
                    )
                    CompetitionParticipationCard(Modifier.padding(bottom = 15.dp)){
                        Column (
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                        ) {
                            Row (
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "سباحة حرة -100 متر-",
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
                            Text(
                                text = "التوقف الأول : 1.54s",
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "التوقف الثاني : 1.54s",
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                    if (isTrainer){
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
                            ParticipationSheetContent(

                            ){
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

@Composable
fun ParticipationSheetContent(onClick : () -> Unit) {
    Column (modifier = Modifier.padding(horizontal = 24.dp)) {
        TextButton(
            onClick = onClick,
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
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.the_results),
            fontFamily = FontFamily(listOf(Font(R.font.cairo_medium))),
            modifier = Modifier.padding(bottom = 12.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        Row(
            modifier = Modifier.padding(bottom = 150.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            var firstStop by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = firstStop,
                onValueChange = {
                    firstStop = it
                },
                label = { Text(stringResource(R.string.first_stop)) },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MyPrimary,
                    focusedContainerColor = MyBackground ,
                    unfocusedContainerColor = MyBackground ,
                    focusedLabelColor = MyPrimary
                )
            )
            var secondStop by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = secondStop,
                onValueChange = {
                    secondStop = it
                },
                label = { Text(stringResource(R.string.second_stop)) },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
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
}


