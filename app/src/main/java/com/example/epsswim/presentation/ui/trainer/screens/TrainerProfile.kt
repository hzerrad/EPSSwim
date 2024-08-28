package com.example.epsswim.presentation.ui.trainer.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.epsswim.R
import com.example.epsswim.data.model.app.trainer.Trainer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.ProfileCard
import com.example.epsswim.presentation.ui.common.viewmodels.AuthViewmodel
import com.example.epsswim.presentation.ui.common.viewmodels.UserViewModel
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.trainer.viewmodels.TrainerViewModel
import com.example.epsswim.presentation.utils.getFullName

@Composable
fun TrainerProfile(
    navController: NavHostController,
    authViewModel: AuthViewmodel,
    trainerViewModel: TrainerViewModel,
    userViewModel: UserViewModel
) {
    val trainerState = trainerViewModel.trainerInfo.collectAsState()
    var trainer by remember {
        mutableStateOf<Trainer?>(null)
    }
    LaunchedEffect(key1 = trainerState.value == null) {
        if (trainerState.value != null){
            trainer = trainerState.value?.data?.trainers?.first()
            Log.d("TAG", "TrainerProfile: $trainer")
        }
    }
    if (trainer != null)
        MainContent(
            navController,
            authViewModel,
            userViewModel,
            trainerViewModel,
            trainer
        )
    else
        CircularProgressIndicator()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    authViewModel: AuthViewmodel,
    userViewModel: UserViewModel,
    trainerViewModel : TrainerViewModel,
    trainer: Trainer?
) {
    val context = LocalContext.current
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri}
    )
    val uploadState by userViewModel.uploadResult.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect (key1 = selectedImage != null) {
        selectedImage?.let {
            isLoading= true
            userViewModel.uploadProfilePicture(it)
        }
    }
    LaunchedEffect(key1 = uploadState) {
        uploadState?.let { result ->
            if (result.isSuccess){
                trainerViewModel.updateTrainerPfp(trainer!!.trainerid,result.getOrDefault(""))
                isLoading= false
                Toast.makeText(context, " تم تحميل الصورة بنجاح", Toast.LENGTH_LONG).show()
            } else {
                isLoading= false
                Toast.makeText(context, " فشل تحميل الصورة", Toast.LENGTH_LONG).show()
            }
        }
    }
    if (isLoading)
        Loading()
    Column (
        modifier = Modifier
            .background(MyBackground)
            .padding(bottom = 50.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
        ) {

            Image(
                painter = painterResource(id = R.drawable.profile_bg),
                contentDescription = "profile background",
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
                    actions = {
                        IconButton(onClick = {
                            authViewModel.logout()
                            navController.navigate(Screen.Login) {
                                popUpTo(Screen.Splash) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.logout_ic),
                                contentDescription = stringResource(id = R.string.logout),
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MyBackground,
                        actionIconContentColor = MyBackground
                    )
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
//                       .align(Alignment.BottomCenter)
                ) {
                    AsyncImage(
                        model = selectedImage,
                        contentDescription = "profile pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = getFullName(trainer!!.firstname,trainer!!.lastname),
                        fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                        color = MyBackground,
                        fontSize = 24.sp,
                    )
                }
            }
        }

        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                title = stringResource(R.string.personal_info),
                icon = R.drawable.personal_info_ic
            ){
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
                            append(trainer!!.birthday)
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
                            append(stringResource(R.string.blood_type))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(trainer!!.bloodtype)
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
                            append(stringResource(R.string.phone_number))
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append(trainer!!.phonenumber)
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
                            append(trainer!!.trainerAbsences_aggregate.aggregate.count.toString())
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
            ){
                trainer!!.trainerAbsences.forEachIndexed { index, trainerAbsence ->
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
                                append(trainerAbsence.absencedate)
                            }
                        },
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
            }
            ProfileCard(
                modifier = Modifier.padding(20.dp),
                title = stringResource(R.string.levels),
                icon = R.drawable.levels_ic,
            ){
                trainer!!.levels.forEachIndexed { index, level ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {

                                append(" المستوى ${index.plus(1)} :")
                            }
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {
                                append(level.levelname)
                            }
                        },
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }

            }

        }

    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier
        .background(color = MyBackground.copy(0.1f))
        .fillMaxSize()){
        CircularProgressIndicator()
    }
}
