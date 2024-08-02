package com.example.epsswim.presentation.ui.common.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyPrimary

@Preview
@Composable
fun ParticipationDetailsScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            MyAppBar(
                title = stringResource(R.string.participation_details),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }){
                        Icon(
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "back button"
                        )
                    }
                }
            ) }
    ) {
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
                            modifier = Modifier.padding(16.dp),
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
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
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
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
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
                }
            }
        }
    }
}

@Composable
fun CompetitionParticipationCard(modifier: Modifier,content: @Composable () -> Unit) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, MyPrimary)
    ){
        content()
    }
}
