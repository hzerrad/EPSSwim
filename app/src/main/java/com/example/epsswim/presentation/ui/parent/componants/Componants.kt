package com.example.epsswim.presentation.ui.parent.componants

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun MyTabRow(selectedIndex: Int, tabsList : List<String>,onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        tabsList.forEachIndexed { index, s ->
            TabItem(
                text = s,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .width(100.dp),
                isSelected = selectedIndex == index
            ) {
                onClick(index)
            }
        }
    }
}

@Composable
fun TabItem(text: String, isSelected :Boolean, modifier: Modifier, onClick: () -> Unit) {

    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            MyBackground
        } else {
            Color.Gray
        },
        animationSpec = tween(easing = LinearEasing),
        label = "Tab text color"
    )
    val tabBgColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            MyPrimary
        } else {
            Color.Gray.copy(0.1f)
        },
        animationSpec = tween(easing = LinearOutSlowInEasing),
        label = "Tab bg color"
    )
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(tabBgColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontFamily = FontFamily(listOf(Font(R.font.cairo_regular))),
            color = tabTextColor,
            fontSize = 16.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SwimmerCard(){
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.height(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
            Row (
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = stringResource(R.string.profile_img),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "محمد عليم",
                        fontFamily = FontFamily(listOf(Font(R.font.cairo_bold))),
                        color = MyPrimaryDark,
                        fontSize = 20.sp,
                    )
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "9 سنوات",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                        )
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(3.dp)
                                .background(Color.LightGray, CircleShape)
                        )
                        Text(
                            text = "مستوى 1",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                        )
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .size(3.dp)
                                .background(Color.LightGray, CircleShape)
                        )
                        Text(
                            text = "5 غيابات",
                            color = Color.LightGray,
                            fontSize = 12.sp,
                        )
                    }
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Normal
                            )
                            ) {
                                append(stringResource(R.string.the_trainer))
                            }
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                            ) {
                                append("العميرات علي")
                            }
                        },
                        color = MyPrimary,
                        fontSize = 12.sp,
                    )
                }
            }
        }

    }
}