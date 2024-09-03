package com.example.epsswim.presentation.ui.common.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.epsswim.data.model.app.swimmer.profile.Competitionswimmer
import com.example.epsswim.data.model.app.swimmer.profile.SwimmersByPk
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.Loading
import com.example.epsswim.presentation.ui.common.componants.ProfileCard
import com.example.epsswim.presentation.ui.common.viewmodels.SharedViewModel
import com.example.epsswim.presentation.ui.common.viewmodels.UserViewModel
import com.example.epsswim.presentation.ui.parent.viewmodels.ParentViewModel
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.utils.calculateAge
import com.example.epsswim.presentation.utils.getFullName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwimmerProfile(
    navController: NavHostController,
    swimmerId: String,
    isParent : Boolean,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    parentViewModel: ParentViewModel
) {
    LaunchedEffect(key1 = swimmerId) {
        sharedViewModel.getSwimmer(swimmerId)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    val swimmerState = sharedViewModel.swimmer.collectAsStateWithLifecycle()
    var swimmer by remember {
        mutableStateOf<SwimmersByPk?>(null)
    }
    var competitions by remember {
        mutableStateOf<List<Competitionswimmer>?>(null)
    }
    LaunchedEffect(swimmerState.value ) {
        if (swimmerState.value != null){
            swimmer = swimmerState.value?.data?.swimmers_by_pk
            competitions = swimmerState.value?.data?.competitionswimmers
        }
    }
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri}
    )
    val uploadState = userViewModel.uploadResult.observeAsState()
    var uploadStateValue by remember {
        mutableStateOf<Result<String>?>(null)
    }
    if (uploadState.value != null)
        uploadStateValue = uploadState.value
    LaunchedEffect (key1 = selectedImage) {
        selectedImage?.let {
            isLoading= true
            userViewModel.uploadProfilePicture(it,swimmerId)
        }
    }
    LaunchedEffect(key1 = uploadState.value) {
        if (uploadState.value != null)
            uploadStateValue = uploadState.value
        uploadStateValue?.let { result ->
            if (result.isSuccess){
                parentViewModel.updateSwimmerPfp(swimmerId,result.getOrDefault(""))
                selectedImage = null
                swimmer = null
                sharedViewModel.getSwimmer(swimmerId)
                if (isLoading){
                    isLoading = false
                    Toast.makeText(context, " تم تحميل الصورة بنجاح", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (isLoading){
                    isLoading = false
                    Toast.makeText(context, " فشل تحميل الصورة", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
    BackHandler {
        uploadStateValue = null
        swimmer = null
        competitions = null
        sharedViewModel.clearState()
        navController.navigateUp()
    }

    if (swimmer == null)
        Loading()
    else
        Column (
            modifier = Modifier
                .background(MyBackground)
                .fillMaxSize()
                .verticalScroll(scrollState)
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
                                uploadStateValue = null
                                swimmer = null
                                competitions = null
                                sharedViewModel.clearState()
                                navController.navigateUp()
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
                        if (isLoading)
                            CircularProgressIndicator()
                        else
                            AsyncImage(
                                model = swimmer!!.pfpUrl,
                                error = painterResource(id = R.drawable.img),
                                fallback =  painterResource(id = R.drawable.img),
                                contentDescription = "profile pic",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(120.dp)
                                    .clickable(enabled = isParent) {
                                        singlePhotoPickerLauncher.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                    }
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
            )
            {
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
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                title = stringResource(R.string.absences),
                icon = R.drawable.calendar_ic,
            ) {
                swimmer!!.swimmerAbsences.forEachIndexed { index, swimmerAbsence ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {

                                append(" الغياب ${index + 1} :")
                            }
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {
                                append(swimmerAbsence.absencedate)
                            }
                        },
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
            }
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 30.dp),
                title = stringResource(R.string.competition),
                icon = R.drawable.competition_profile_ic,
                isLastCard = true,
                onScrollStateChange = { condition ->
                    scope.launch {
                        if (condition){
                            delay(250)
                            scrollState.animateScrollTo(scrollState.maxValue, tween(500))
                        }
                    }
                }
            ){
                competitions!!.forEach {
                    CompetitionCard(
                        modifier=Modifier.padding(bottom = 16.dp),
                        competition=it.competition
                    ){
                        navController.navigate(Screen.ParticipationDetails(swimmerId, competitionID = it.competition.competitionid))
                    }
                }

            }

        }


}

