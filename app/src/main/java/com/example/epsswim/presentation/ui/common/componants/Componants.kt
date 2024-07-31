package com.example.epsswim.presentation.ui.common.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.epsswim.R
import com.example.epsswim.presentation.navigation.BottomBarItem
import com.example.epsswim.presentation.ui.theme.MyBackground
import com.example.epsswim.presentation.ui.theme.MyPrimary
import com.example.epsswim.presentation.ui.theme.MyPrimaryDark

@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange.invoke(it)
            },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = trailingIcon
            ,
            label = label,
            visualTransformation = visualTransformation,
            modifier = modifier,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
){
    CenterAlignedTopAppBar(
        title = { 
            Text(
                text = title,
                fontFamily = FontFamily(listOf(Font(R.font.cairo_semi_bold))),
                fontSize = 24.sp,
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = MyBackground,
            actionIconContentColor = MyBackground,
            navigationIconContentColor = MyBackground,
            containerColor = MyPrimaryDark
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MyBottomBar() {
    val navController: NavHostController = rememberNavController()
    Surface (
        modifier = Modifier
            .padding(start = 20.dp, bottom = 25.dp, end = 20.dp) ,
        shape = RoundedCornerShape(60.dp),
        color = MyBackground,
        contentColor = MyBackground,
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
    ){
        BottomAppBar(
            containerColor = MyBackground,
            contentColor = Color(0xff9DB2CE)
        ){
            val items = listOf(
                BottomBarItem.Competitions,
                BottomBarItem.Absences,
                BottomBarItem.Profile,
            )
            val destination = navController.currentBackStackEntryAsState().value?.destination
            Row(
                modifier = Modifier
                    .padding( horizontal = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach {  item ->
//                    val selected = item.route== destination?.route?.split("?")?.first()
                    IconButton(onClick = {
//                        navController.navigate(item.route) {
//                            popUpTo(navController.graph.findStartDestination().id)
//                            launchSingleTop = true
//                        }
                    }) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "icon",
                            tint = MyPrimary,
//                            tint = if (selected) PrimaryColor else onPrimaryColor,
                        )
                    }
                }
            }
        }

    }
}