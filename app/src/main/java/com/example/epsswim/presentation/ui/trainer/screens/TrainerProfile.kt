package com.example.epsswim.presentation.ui.trainer.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.epsswim.R
import com.example.epsswim.data.model.app.trainer.Trainer
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.Loading
import com.example.epsswim.presentation.ui.common.componants.ProfileCard
import com.example.epsswim.presentation.ui.common.viewmodels.AuthViewmodel
import com.example.epsswim.presentation.ui.common.viewmodels.UserViewModel
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.trainer.viewmodels.TrainerViewModel
import com.example.epsswim.presentation.utils.getFullName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TrainerProfile(
    navController: NavHostController,
    authViewModel: AuthViewmodel,
    trainerViewModel: TrainerViewModel,
    userViewModel: UserViewModel
) {
    LaunchedEffect(true) {
        trainerViewModel.getTrainerInfo()
    }
    val trainerState = trainerViewModel.trainerInfo.collectAsState()
    var trainer by remember {
        mutableStateOf<Trainer?>(null)
    }

    LaunchedEffect(key1 = trainerState.value) {
        if (trainerState.value != null){
            trainer = trainerState.value?.data?.trainers?.first()
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
        Loading()
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
    val scope = rememberCoroutineScope()
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val imageCropLauncher =
        rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
            if (result.isSuccessful) {
                selectedImage = result.uriContent

            } else {
                println("ImageCropping error: ${result.error}")
            }
        }
    val uploadState by userViewModel.uploadResult.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect (key1 = selectedImage) {
        selectedImage?.let {
            isLoading = true
            userViewModel.uploadProfilePicture(it,trainer!!.trainerid)
        }
    }
    LaunchedEffect(key1 = uploadState) {
        uploadState?.let { result ->
            if (result.isSuccess){
                trainerViewModel.updateTrainerPfp(trainer!!.trainerid,result.getOrDefault(""))
                selectedImage = null
                if (isLoading){
                    isLoading = false
                    Toast.makeText(context, " تم تحميل الصورة بنجاح", Toast.LENGTH_LONG).show()
                }
            } else {
                if (isLoading){
                    isLoading = false
                    Toast.makeText(context, " فشل تحميل الصورة", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    Column(
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
                            userViewModel.clearState()
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
                    if (isLoading)
                        CircularProgressIndicator()
                    else
                        AsyncImage(
                            model = if (trainer!!.pfpUrl.contains(trainer.trainerid)) trainer.pfpUrl else null,
                            error = painterResource(id = R.drawable.img),
                            fallback =  painterResource(id = R.drawable.img),
                            contentDescription = "profile pic",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp)
                                .clickable {
                                    val cropOptions = CropImageContractOptions(
                                        null,
                                        CropImageOptions(imageSourceIncludeCamera = false)
                                    )
                                    imageCropLauncher.launch(cropOptions)
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
        val scrollState = rememberScrollState()
        Column (
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
        ) {
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                title = stringResource(R.string.personal_info),
                icon = R.drawable.personal_info_ic,
            )
            {
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
            ) {
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

