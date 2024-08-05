package com.example.epsswim.presentation.ui.trainer.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.common.componants.CompetitionCard
import com.example.epsswim.presentation.ui.common.componants.MyAppBar
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary

@Preview(showBackground = true)
@Composable
fun CompetitionsScreen(){
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold (
            topBar = { MyAppBar(title = stringResource(R.string.the_competitions)) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { /*TODO*/ },
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
            val keyboardController = LocalSoftwareKeyboardController.current
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

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MySearchBar(
    text : MutableState<String> = mutableStateOf(""),
    onSearchClicked: (String) -> Unit = {},
    onTextChange: (String) -> Unit = {},
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth(),
        query = text.value,
        onQueryChange = { onTextChange(it) },
        onSearch = {
            onSearchClicked(it)
        },
        active = false,
        onActiveChange = {},
        placeholder = { Text(text = stringResource(R.string.search)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(id = R.string.search)) },
        trailingIcon = {
            IconButton(onClick = { text.value = ""}) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
            }
        },
        shadowElevation = 10.dp,
        shape = SearchBarDefaults.inputFieldShape,
        windowInsets = WindowInsets(top = 0),
        colors = SearchBarDefaults.colors(containerColor = Color.White),
        content = {},
    )
}