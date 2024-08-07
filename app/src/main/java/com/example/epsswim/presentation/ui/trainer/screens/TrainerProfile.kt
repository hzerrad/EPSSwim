package com.example.epsswim.presentation.ui.trainer.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.ProfileCard
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.trainer.componants.LogoutCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerProfile(navController: NavHostController) {
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
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.chevron_left),
                                contentDescription = "back button"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MyBackground,
                        navigationIconContentColor = MyBackground
                    )
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
//                       .align(Alignment.BottomCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "profile pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "محمد عليم",
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

                            append("الجنس : ")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("ذكر")
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
                            append("12/12/1990")
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
                            append("A+")
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
                            append("055454545454")
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
                            append('3')
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
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(" الغياب 1 :")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("12/12/2023")
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(" الغياب 2 :")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("12/12/2023")
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
            ProfileCard(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                title = stringResource(R.string.levels),
                icon = R.drawable.levels_ic,
            ){
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(" المستوى 1 :")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("المبتدئين")
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {

                            append(" المستوى 2 :")
                        }
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ) {
                            append("المحترفين")
                        }
                    },
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
            LogoutCard(modifier = Modifier.padding(20.dp)) {

            }
        }

    }
}