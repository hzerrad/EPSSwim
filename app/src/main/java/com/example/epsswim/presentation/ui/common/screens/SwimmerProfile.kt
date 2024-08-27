package com.example.epsswim.presentation.ui.common.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.epsswim.R
import com.example.epsswim.data.model.app.swimmer.Swimmer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.ProfileCard
import com.example.epsswim.presentation.ui.common.viewmodels.SharedViewModel
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.utils.calculateAge
import com.example.epsswim.presentation.utils.getFullName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwimmerProfile(
    navController: NavHostController,
    swimmerId: String,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = swimmerId) {
        sharedViewModel.getSwimmer(swimmerId)
    }
    val swimmerState = sharedViewModel.swimmer.collectAsStateWithLifecycle()
    var swimmer by remember {
        mutableStateOf<Swimmer?>(null)
    }
    if (swimmerState.value != null){
        swimmer = swimmerState.value!!.data.swimmers.first()
        Log.d("TAG", "SwimmerProfile: $swimmer")
    }
    if (swimmer != null)
        Column (
            modifier = Modifier
                .background(MyBackground)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp ))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_bg),
                    contentDescription ="profile background" ,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(350.dp)
                )
                Column {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
//                       .align(Alignment.TopCenter),
                        title = {
                            Text(
                                text = stringResource(R.string.profile),
                                fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                                fontSize = 24.sp,
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                swimmer = null
                                navController.popBackStack()
                            }){
                                Icon(
                                    painter = painterResource(id = R.drawable.chevron_left),
                                    contentDescription = "back button"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = androidx.compose.ui.graphics.Color.Transparent,
                            titleContentColor = MyBackground,
                            navigationIconContentColor = MyBackground
                        )
                    )
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
//                       .align(Alignment.BottomCenter)
                    ) {
                        AsyncImage(
                            model = swimmer!!.pfpUrl,
                            contentDescription ="profile pic" ,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = getFullName(swimmer!!.firstname,swimmer!!.lastname),
                            fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                            color = MyBackground,
                            fontSize = 24.sp,
                        )
                    }
                }
            }
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp),
                title = stringResource(R.string.personal_info),
                icon = R.drawable.personal_info_ic
            ){
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(stringResource(R.string.level))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(swimmer!!.level.levelname)
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append("الجنس : ")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(swimmer!!.sex)
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)

                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append("تاريخ الميلاد : ")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(swimmer!!.birthday)
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)

                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(stringResource(R.string.age))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(calculateAge(swimmer!!.birthday).toString())
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)

                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(stringResource(R.string.trainer_name))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(getFullName(swimmer!!.trainer.firstname,swimmer!!.trainer.lastname))
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(stringResource(R.string.trainer_phone))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(swimmer!!.trainer.phonenumber)
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 14.dp)

                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(stringResource(R.string.absence_number))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(swimmer!!.swimmerAbsences_aggregate.aggregate.count.toString())
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp),
                title = stringResource(R.string.competition),
                icon = R.drawable.competition_profile_ic
            ){
                CompetitionCard(
                    modifier=Modifier.padding(bottom = 16.dp),
                    name="المسابقة الولائية",
                    date = "12/08/2023"
                ){
                    navController.navigate(Screen.ParticipationDetails)
                }
                CompetitionCard(
                    modifier=Modifier,
                    name="المسابقة الولائية",
                    date = "12/08/2023"
                ){
                    navController.navigate(Screen.ParticipationDetails)
                }
            }
        }
    else
        CircularProgressIndicator()

}

