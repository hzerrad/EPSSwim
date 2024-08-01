package com.example.epsswim.presentation.ui.parent.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyPrimaryDark

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwimmerProfile(){
    Column (
        modifier = Modifier
            .background(MyBackground)
            .fillMaxSize()
    ){
        Box {

            Image(
                painter = painterResource(id = R.drawable.profile_bg),
                contentDescription ="profile background" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                title = {
                    Text(
                        text = stringResource(R.string.profile),
                        fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                        fontSize = 24.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }){
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
                    .padding(bottom = 25.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription ="profile pic" ,
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
                        append("1")
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
                        append("12/12/2012")
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

                        append(stringResource(R.string.level))
                    }
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                    ) {
                        append("1")
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

                        append(stringResource(R.string.trainer_name))
                    }
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                    ) {
                        append("العميرات علي")
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

                        append(stringResource(R.string.trainer_phone))
                    }
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                    ) {
                        append("055454545454")
                    }
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {

                                append(stringResource(R.string.abscence_number))
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
                },
                color = Color.Black,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun ProfileCard(modifier: Modifier, title: String, icon: Int, content: @Composable () -> Unit) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    ElevatedCard(
        onClick = { isClicked = !isClicked },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ){
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(id = if (isClicked) R.drawable.chevron_up else R.drawable.chevron_down),
                contentDescription = "icon",
                tint = MyPrimaryDark
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = title,
                    fontFamily = FontFamily(listOf(Font(R.font.cairo_bold))),
                    color = MyPrimaryDark,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    tint = MyPrimaryDark
                )
            }
        }
        if (isClicked)
            Box(
                modifier = Modifier
                    .size(1.dp)
                    .background(MyBackground)
                    .fillMaxWidth()
            )
        AnimatedVisibility(visible = isClicked) {
            Column (modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
                    content()
                }
            }
        }
    }
}
