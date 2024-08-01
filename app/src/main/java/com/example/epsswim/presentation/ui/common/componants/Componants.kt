package com.example.epsswim.presentation.ui.common.componants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
@Composable
fun CompetitionCard(modifier: Modifier,name: String, date: String, onClick: () -> Unit) {
    OutlinedCard(
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(0.6.dp, Color.LightGray)
    ){
        Row (
            modifier= Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = name,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                )
                Text(
                    text = date,
                    color = Color.Gray,
                    fontSize = 13.sp,
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
                    .padding(bottom = 1.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)

            )
        AnimatedVisibility(visible = isClicked) {
            Column (
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
                    content()
                }
            }
        }
    }
}
