package com.example.epsswim.presentation.ui.common.componants

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

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
