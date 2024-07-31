package com.example.epsswim.presentation.ui.common.componants

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.epsswim.R
import com.example.epsswim.presentation.ui.theme.MyBackground
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