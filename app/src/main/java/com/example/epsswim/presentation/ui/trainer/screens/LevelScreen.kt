package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.epsswim.R
import com.example.epsswim.presentation.navigation.Screen
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyPrimaryDark
import com.example.epsswim.presentation.ui.theme.MyRed
import com.example.epsswim.presentation.ui.theme.MySecondary
import com.example.epsswim.presentation.ui.trainer.componants.MyWeekCalendar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LevelScreen (

){
    Scaffold (
        topBar = {
            MyAppBar(
                title = "المستوى 1",
                navigationIcon = {
                    IconButton( onClick = {}){
                        Icon(
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "back button"
                        )
                    }
                },
                actions = {
                    IconButton( onClick = {}){
                        Icon(
                            painter = painterResource(id = R.drawable.done_ic),
                            contentDescription = "done button"
                        )
                    }
                }
            )
        }

    ){
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        var note by remember {
            mutableStateOf("")
        }
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            Column (horizontalAlignment = Alignment.End){
                MyWeekCalendar()
                Text(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
                    text = stringResource(R.string.number_of_presence) +"23/23",
                    fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                    fontSize = 20.sp,
                )
                Box (modifier = Modifier.fillMaxWidth()){
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .clickable { }
                            .width(28.dp)
                            .height(180.dp)
                    ) {
                       Icon(
                           modifier = Modifier
                               .align(Alignment.CenterStart)
                               .padding(start = 10.dp),
                           painter = painterResource(id = R.drawable.chevron_left) ,
                           contentDescription ="back",
                           tint = MyPrimaryDark
                       )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { }
                            .width(28.dp)
                            .height(80.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.chevron_right) ,
                            contentDescription ="next",
                            tint = MyPrimaryDark
                        )
                    }
                    AbsenceSwimmerCard(
                        modifier = Modifier
                            .padding(horizontal = 56.dp)
                            .align(Alignment.Center)
                    ){
                    }
                }
                Button(
                    onClick = {
                        showBottomSheet = true
                    },
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 56.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MyPrimaryDark, contentColor = MyBackground),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                ) {
                    Text(
                        text = stringResource(R.string.add_note),
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = MyBackground
                    )
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState,
                        containerColor = MyBackground
                    ) {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                            Column {
                                TextButton(
                                    onClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            showBottomSheet= false
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(top = 16.dp, end = 16.dp)
                                        .align(Alignment.End)
                                ) {
                                    Text(
                                        text ="حفظ",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        color = MyPrimary
                                    )
                                }

                                TextField (
                                    value = note ,
                                    onValueChange = {
                                        note=it
                                    },
                                    placeholder = {
                                        Text(text = "أضف ملاحظة...")
                                    },
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    minLines = 10,
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = MyBackground,
                                        unfocusedContainerColor = MyBackground,
                                        cursorColor = MyPrimaryDark,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    )
                                )

                            }
                        }

                    }
                }
            }
        }
    }
}
@Composable
fun AbsenceSwimmerCard(modifier: Modifier,onClick:() -> Unit){
    var selected by remember {
        mutableStateOf(false)
    }

    Box(modifier = modifier.fillMaxWidth()){
        Surface(
            modifier = Modifier
                .padding(top = 65.dp)
                .align(Alignment.TopCenter),
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 10.dp,
            shadowElevation = 10.dp,
            color = MySecondary,
            onClick = onClick
        ) {
            Column (
                Modifier
                    .padding(top = 65.dp)
                    .fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp),
                    text = "محمد عليم",
                    fontFamily = FontFamily(listOf(Font(R.font.cairo_bold))),
                    fontSize = 20.sp,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 40.dp, bottom = 12.dp),
                    text =   stringResource(id = R.string.level) + "1",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 40.dp, bottom = 12.dp),
                    text = stringResource(R.string.age) + "11",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 40.dp, bottom = 12.dp),
                    text = stringResource(id = R.string.abscence_number) + "3",
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                )
                val buttonColor = if (!selected) MyRed else MyBackground
                val buttonContainerColor = if (selected) MyRed else MyBackground
                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 40.dp, bottom = 40.dp)
                        .align(Alignment.Start),
                    onClick = { selected = !selected },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = buttonColor,
                        containerColor = buttonContainerColor
                    ),
                    border = BorderStroke(1.dp, buttonColor)
                ) {
                    Text(
                        text = stringResource(R.string.absent),
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .background(MySecondary, RoundedCornerShape(12.dp))
                .align(Alignment.TopCenter)
                .size(125.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "swimmer image",
            contentScale = ContentScale.Crop
        )
    }
}