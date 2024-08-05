package com.example.epsswim.presentation.ui.trainer.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MySecondary
import com.example.epsswim.presentation.ui.trainer.componants.MySearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CompetitionsScreen(){
    val keyboardController = LocalSoftwareKeyboardController.current
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold (
            topBar = { MyAppBar(title = stringResource(R.string.the_competitions)) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {  },
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
                }
            }
        }
    }
}

@Composable
fun ParticipantCard(modifier: Modifier,onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, MySecondary)
    ){
        Row (
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
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
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "مستوى جونيور (18 سنة)",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CompetitionDetailsCard(modifier: Modifier) {
    Row (
        modifier= modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "المسابقة الولائية",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
            )
            Text(
                text = "12/12/2023",
                color = Color.Gray,
                fontSize = 16.sp,
            )
            Text(
                text = "سطيف, عين أرنات",
                color = Color.Gray,
                fontSize = 16.sp,
            )
        }
        Image(
            modifier = Modifier.height(50.dp),
            painter = painterResource(id = R.drawable.competition_badge),
            contentDescription = "competition badge",
            contentScale = ContentScale.Crop
        )
    }
}

